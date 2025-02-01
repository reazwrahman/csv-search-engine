package utility;

import bu.cs622.csv.search.engine.utility.FileHandler;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Name: Reaz W. Rahman
 * Course: CS 622: Advanced Programming Techniques
 * Date: 1/31/2025
 * File name: FilesMerger.java
 * Description: This class tests the m_fileHandler class.
 */

public class FileHandlerTest {

    private static String inputFile;
    private static String outputFile;
    private static String fullInputPath;
    private static String tempFile;
    private static String INPUT_PATH;
    private static FileHandler m_fileHandler;


    @BeforeAll
    public static void setup() {
        ClassLoader classLoader = FileHandlerTest.class.getClassLoader();
        INPUT_PATH = Paths.get(classLoader.getResource("inputs").getPath()).toString();
        inputFile = "hello_world.csv";
        outputFile = "test_output.csv";
        tempFile = "temp.txt";
        fullInputPath = Paths.get(INPUT_PATH, inputFile).toString();
        m_fileHandler = new FileHandler();

        deleteFile(outputFile);
        deleteFile(tempFile);
    }


    @Test
    public void testGetFilesList() {
        List<String> files = m_fileHandler.getFilesList(INPUT_PATH);
        assert (files.size() == 2);
    }

    @Test
    public void testRecreateFile() {
        assertDoesNotThrow(() -> m_fileHandler.recreateFile(outputFile));
        File file = new File(outputFile);
        assert file.exists();
    }

    @Test
    public void testRecreateFileWithException() throws IOException {
        File mockFile = Mockito.mock(File.class); // mocking the Java.io.File

        // Mock the behavior of the File methods
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.delete()).thenReturn(true);
        when(mockFile.createNewFile()).thenThrow(new IOException("Failed to create new file"));

        // Use a spy to partially mock the FileHandler class
        FileHandler fileHandlerSpy = Mockito.spy(new FileHandler());
        doReturn(mockFile).when(fileHandlerSpy).getFileObject(anyString());

        // Assert that an IOException is thrown
        assertThrows(IOException.class, () -> fileHandlerSpy.recreateFile("test123.txt"));

    }

    @Test
    public void testReadLinesWithException() throws IOException {
        BufferedReader mockReader = Mockito.mock(BufferedReader.class); // mocking the Java.io.BufferedReader

        // Mock the behavior
        when(mockReader.readLine()).thenThrow(new IOException("Failed to read file"));

        // Use a spy to partially mock the FileHandler class
        FileHandler fileHandlerSpy = Mockito.spy(new FileHandler());
        doReturn(mockReader).when(fileHandlerSpy).getBufferedReader(anyString());

        // Assert that an IOException is thrown
        assertThrows(IOException.class, () -> fileHandlerSpy.readLines("test123.txt"));

    }

    @Test
    public void testReadCsvWithException() throws CsvException, IOException {
        CSVReader mockReader = Mockito.mock(CSVReader.class);

        // Mock the behavior
        when(mockReader.readAll()).thenThrow(new CsvException("Failed to read file"));

        // Use a spy to partially mock the FileHandler class
        FileHandler fileHandlerSpy = Mockito.spy(new FileHandler());
        doReturn(mockReader).when(fileHandlerSpy).getCsvReader(anyString());

        // Assert that an RuntimeException is thrown
        assertThrows(RuntimeException.class, () -> fileHandlerSpy.readCsv("test123.txt"));

    }

    @Test
    public void testCopyFileWithHeader() throws IOException {
        deleteFile(outputFile);
        assertDoesNotThrow(() -> m_fileHandler.copyFile(fullInputPath, outputFile, false));

        // match the content
        List<String> inputLines = m_fileHandler.readLines(fullInputPath);
        List<String> outputLines = m_fileHandler.readLines(outputFile);

        assert (inputLines.size() == 2);
        assert (inputLines.equals(outputLines));

        assertThrows(Exception.class, () -> {
            // Code that is expected to throw the exception
            m_fileHandler.copyFile("java123", "java456", true);
        });

    }

    @Test
    public void testCopyFileWithoutHeader() throws IOException {
        deleteFile(outputFile);

        assertDoesNotThrow(() -> m_fileHandler.copyFile(fullInputPath, outputFile, true));

        // match the content
        List<String> inputLines = m_fileHandler.readLines(fullInputPath);
        List<String> outputLines = m_fileHandler.readLines(outputFile);

        assert (inputLines.size() == 2 && outputLines.size() == 1);
        assert (inputLines.get(1).equals(outputLines.get(0)));

    }

    @Test
    public void testWriteContent() throws IOException {
        BufferedWriter mockWriter = Mockito.mock(BufferedWriter.class);

        // Mock the mockWriter
        doThrow(new IOException("Failed to write to file")).when(mockWriter).write(anyString());

        // Use a spy to partially mock the FileHandler class
        FileHandler fileHandlerSpy = Mockito.spy(new FileHandler());
        doReturn(mockWriter).when(fileHandlerSpy).getBufferedWriter(anyString(), anyBoolean());

        // Assert that an IOException is thrown
        assertThrows(IOException.class, () -> fileHandlerSpy.writeContent("test123.txt", "hello_world"));
    }

    @Test
    public void testWriteContentWithException() throws IOException {
        deleteFile(tempFile);
        String testData = "sample test data";
        String tempFile = "temp.txt";
        m_fileHandler.writeContent(tempFile, testData);

        // match content
        List<String> lines = m_fileHandler.readLines(tempFile);
        assert (lines.size() == 1);
        assert (lines.get(0).equals(testData));
    }

    @Test
    public void testReadCsvData() {
        List<String[]> data = m_fileHandler.readCsv(fullInputPath);
        assert (data.size() == 2);
        assert (data.get(1).length == 2);
        assert (data.get(1)[0].equals("hello"));
        assert (data.get(1)[1].equals("world"));
    }


    @AfterAll
    public static void cleanup() {
        deleteFile(outputFile);
        deleteFile(tempFile);
    }

    // ---------------- helper methods ------------ //
    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}

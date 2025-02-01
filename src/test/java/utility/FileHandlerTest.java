package utility;

import bu.cs622.csv.search.engine.utility.FileHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @AfterAll
    public static void cleanup() {
        deleteFile(outputFile);
        deleteFile(tempFile);
    }

    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
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
    public void testCopyFileWithHeader() {
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
    public void testCopyFileWithoutHeader() {
        deleteFile(outputFile);

        assertDoesNotThrow(() -> m_fileHandler.copyFile(fullInputPath, outputFile, true));

        // match the content
        List<String> inputLines = m_fileHandler.readLines(fullInputPath);
        List<String> outputLines = m_fileHandler.readLines(outputFile);

        assert (inputLines.size() == 2 && outputLines.size() == 1);
        assert (inputLines.get(1).equals(outputLines.get(0)));

    }

    @Test
    public void testWriteContent() {
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
}

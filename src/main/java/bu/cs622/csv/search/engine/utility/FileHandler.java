package bu.cs622.csv.search.engine.utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Reaz W. Rahman
 * Course: CS 622: Advanced Programming Techniques
 * Date: 1/31/2025
 * File name: FileHandler.java
 * Description: This class is responsible for handling file operations.
 */

public class FileHandler {

    // Get a list of files in a directory
    public List<String> getFilesList(String path) {
        List<String> fileList = new ArrayList<>();
        File directory = new File(path);

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileList.add(file.getName());
                    }
                }
            }
        }

        return fileList;
    }

    // Create a new file or overwrite an existing file
    public void recreateFile(String fileName) throws IOException {
        try {
            File file = getFileObject(fileName);
            // Delete the file if it exists
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            throw new IOException("FileHandler::recreateFile failed to create file");
        }
    }

    // Copy the content of one file to another
    public void copyFile(String inputFilePath, String outputFilePath, boolean ignoreHeader) throws RuntimeException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {

            boolean firstLineSeen = false;
            String line;
            while ((line = reader.readLine()) != null) {
                if (ignoreHeader && !firstLineSeen) {
                    firstLineSeen = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("FileHandler::copyFile failed to copy, exception: " + e.getCause());
        }
    }

    // Read all lines from a file, not recommended for large files
    @Deprecated
    public List<String> readLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = getBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
        return lines;
    }

    // Read all CSV data from a file, not recommended to use for large files
    @Deprecated
    public List<String[]> readCsv(String filePath) {
        try (CSVReader reader = getCsvReader(filePath)) {
            return reader.readAll();  // Reads entire CSV with multi-line support
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error reading CSV", e);
        }
    }

    // Write content to a file
    public void writeContent(String filePath, String content) throws IOException {
        try (BufferedWriter writer = getBufferedWriter(filePath, true)) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            throw new IOException(e.getCause());
        }
    }

    /* Method to create a File object, refactored to make the class testable with mocks */
    public File getFileObject(String fileName) {
        return new File(fileName);
    }

    /* Method to create a BufferedReader object, refactored to make the class testable with mocks */
    public BufferedReader getBufferedReader(String filePath) throws FileNotFoundException {
        return new BufferedReader(new FileReader(filePath));
    }

    /* Method to create a BufferedWriter object, refactored to make the class testable with mocks */
    public BufferedWriter getBufferedWriter(String filePath, boolean shouldAppend) throws IOException {
        return new BufferedWriter(new FileWriter(filePath, shouldAppend));
    }

    /* Method to create a CSVReader object, refactored to make the class testable with mocks */
    public CSVReader getCsvReader(String filePath) throws IOException {
        return new CSVReader(new FileReader(filePath));
    }
}

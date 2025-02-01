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
        File file = new File(fileName);

        // Delete the file if it exists
        if (file.exists()) {
            file.delete();
        }

        // Create a new empty file
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Copy the content of one file to another
    public void copyFile(String inputFilePath, String outputFilePath, boolean ignoreHeader) {
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
            e.printStackTrace();
        }
    }

    // Read all lines from a file, not recommended for large files
    @Deprecated
    public List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    // Read all CSV data from a file, not recommended to use for large files
    @Deprecated
    public List<String[]> readCsv(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            return reader.readAll();  // Reads entire CSV with multi-line support
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error reading CSV", e);
        }
    }

    // Write content to a file
    public void writeContent(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

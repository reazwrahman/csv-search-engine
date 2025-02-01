package bu.cs622.csv.search.engine;

import bu.cs622.csv.search.engine.utility.Configs;
import bu.cs622.csv.search.engine.utility.FileHandler;

import java.nio.file.Paths;
import java.util.List;

/**
 * Name: Reaz W. Rahman
 * Course: CS 622: Advanced Programming Techniques
 * Date: 1/31/2025
 * File name: FilesMerger.java
 * Description: This class is responsible for merging input files into a single output file.
 */


public class FilesMerger {
    private final FileHandler m_fileHandler;

    public FilesMerger() {
        m_fileHandler = new FileHandler();
    }

    // Merge all input files into a single output file
    public void mergeInputFiles() throws Exception {
        m_fileHandler.recreateFile(Configs.OUTPUT_FILE);
        m_fileHandler.writeContent(Configs.OUTPUT_FILE, Configs.HEADERS); // write headers once, ignore after
        List<String> inputFiles = m_fileHandler.getFilesList(Configs.INPUT_PATH);
        for (String file : inputFiles) {
            String fullInputPath = Paths.get(Configs.INPUT_PATH, file).toString();
            m_fileHandler.copyFile(fullInputPath, Configs.OUTPUT_FILE, true);
        }
    }
}

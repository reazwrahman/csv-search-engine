package bu.cs622.csv.search.engine;

import bu.cs622.csv.search.engine.utility.Configs;
import bu.cs622.csv.search.engine.utility.FileHandlingException;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            FilesMerger merger = new FilesMerger();
            merger.mergeInputFiles();

            // process output file
            Processor processor = new Processor();
            processor.search(Configs.OUTPUT_FILE, "united states", false);

        } catch (FileHandlingException ex) {
            ex.printStackTrace();
        }
    }
}
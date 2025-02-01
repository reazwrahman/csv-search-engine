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
            processor.search(Configs.OUTPUT_FILE, "deepseek", false);

        } catch (FileHandlingException ex) {
            ex.printStackTrace();
            File file = new File(Configs.OUTPUT_FILE);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } // end of main
} // end of class
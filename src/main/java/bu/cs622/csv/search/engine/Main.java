package bu.cs622.csv.search.engine;

import bu.cs622.csv.search.engine.utility.Configs;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilesMerger merger = new FilesMerger();
        merger.mergeInputFiles();

        Processor processor = new Processor();
        processor.search(Configs.OUTPUT_FILE, "hello world", false);

    }
}
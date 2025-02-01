package bu.cs622.csv.search.engine;

import bu.cs622.csv.search.engine.utility.Configs;

public class Main {
    public static void main(String[] args) throws Exception {
        FilesMerger merger = new FilesMerger();
        merger.mergeInputFiles();

        Processor processor = new Processor();
        processor.search(Configs.OUTPUT_FILE, "united states", false);

    }
}
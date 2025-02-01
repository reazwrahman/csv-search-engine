package bu.cs622;

import bu.cs622.utility.Configs;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilesMerger merger = new FilesMerger();
        merger.mergeInputFiles();

        Processor processor = new Processor();
        processor.search(Configs.OUTPUT_FILE, "cricket", false);

    }
}
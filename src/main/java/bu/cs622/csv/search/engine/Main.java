package bu.cs622.csv.search.engine;

import bu.cs622.csv.search.engine.utility.Configs;
import bu.cs622.csv.search.engine.utility.FileHandlingException;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            FilesMerger merger = new FilesMerger();
            merger.mergeInputFiles();
            Processor processor = new Processor();

            // process output file
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a search term: ");
                processor.search(Configs.OUTPUT_FILE, scanner.nextLine(), false);
            }

        } catch (FileHandlingException ex) {
            ex.printStackTrace();
        }
    }
}
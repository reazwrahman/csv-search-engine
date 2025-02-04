package bu.cs622.csv.search.engine;

import bu.cs622.csv.search.engine.utility.Configs;
import bu.cs622.csv.search.engine.utility.SearchHistory;
import bu.cs622.csv.search.engine.utility.Tuple;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Name: Reaz W. Rahman
 * Course: CS 622: Advanced Programming Techniques
 * Date: 1/31/2025
 * File name: Processor.java
 * Description: This class is responsible for searching for a keyword in the output file.
 */

public class Processor {
    int m_matchCount;
    int m_expectedLength = Configs.HEADERS.split(",").length;
    Map<String, Tuple> m_searchHistory = new HashMap<>();

    // Search for a keyword in the output file
    public int search(String outputFile, String keyword, boolean isCaseSensitive) {
        m_matchCount = 0;
        SearchHistory.insertRecord(keyword);

        processCsv(outputFile, keyword, isCaseSensitive);
        if (m_matchCount == 0) {
            System.out.println("No match found for: " + keyword);
        }
        SearchHistory.printHistory();

        // put it to map
//        int frequency = 1;
//        if (m_searchHistory.containsKey(keyword)) {
//            frequency = (int) m_searchHistory.get(keyword).frequency;
//        }
//
//        Tuple record = new Tuple(frequency, Instant.now());
//        m_searchHistory.put(keyword, record);
//        m_searchHistory.forEach((key, value) -> System.out.println("search term: " + key + ", frequency: " + value.frequency
//                + ", last searched: " + value.lastSearched.toString()));
//        System.out.println("-----------------------------------");
        // -------------- //
        return m_matchCount;
    }

    // Process a CSV file
    private void processCsv(String filePath, String keyword, boolean isCaseSensitive) {
        boolean isHeader = true;
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] row;
            while ((row = reader.readNext()) != null) {
                if (isHeader) { // skip the header row
                    isHeader = false;
                } else {
                    checkLength(row);
                    searchForKeyWord(row, keyword, isCaseSensitive);
                }
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error reading CSV", e);
        }
    }

    // Check if the row has the expected length
    private void checkLength(String[] row) {
        int rowLength = row.length;
        if (rowLength != m_expectedLength) {
            String message = "OutputProcessor::checkLength invalid length for row, expected: " + m_expectedLength;
            message += " Found: " + rowLength + " , data: " + Arrays.toString(row);
            throw new RuntimeException(message);
        }
    }

    // Search for a keyword in a row
    private void searchForKeyWord(String[] row, String wordToFind, boolean isCaseSensitive) {
        StringBuilder fullString = new StringBuilder();
        for (String item : row) {
            fullString.append(item);
            fullString.append(" "); // space between each column
        }

        // Create the regex pattern to search for the word
        Pattern pattern;
        if (isCaseSensitive) {
            pattern = Pattern.compile("\\b" + Pattern.quote(wordToFind) + "\\b");
        } else {
            pattern = Pattern.compile("\\b" + Pattern.quote(wordToFind) + "\\b", Pattern.CASE_INSENSITIVE);
        }

        // Create a matcher to find the word in the StringBuilder
        Matcher matcher = pattern.matcher(fullString.toString());

        // Check if the word exists in the StringBuilder
        if (matcher.find()) {
            m_matchCount++;
            System.out.println("Found: " + wordToFind);
            System.out.println("Match count: " + m_matchCount);
            System.out.println("funds_raised_amount: " + row[Configs.FUNDS_RAISED_AMOUNT_INDEX]);
            System.out.println("close_date: " + row[Configs.CLOSE_DATE_INDEX]);
            System.out.println("full row: " + Arrays.toString(row));
            System.out.println("-----------------------------------");
        }

    }
}

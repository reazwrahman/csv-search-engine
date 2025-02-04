package bu.cs622.csv.search.engine.utility;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Name: Reaz W. Rahman
 * Course: CS 622: Advanced Programming Techniques
 * Date: 2/03/2025
 * File name: SearchHistory.java
 * Description: This class is responsible for maintaining search history of the search terms.
 */

public class SearchHistory {

    private static Map<String, Tuple> m_searchHistory = new HashMap<>();

    // Insert a record in the search history
    public static void insertRecord(String keyword){
        int frequency = 1;
        if (m_searchHistory.containsKey(keyword)) {
            frequency = (int) m_searchHistory.get(keyword).frequency + 1;
        }

        Tuple record = new Tuple(frequency, Instant.now());
        m_searchHistory.put(keyword, record);
    }

    // Get the search history
    public static Map<String, Tuple> getSearchHistory(){
        return m_searchHistory;
    }

    // Print the search history
    public static void printHistory(){
        System.out.println("-----------------------------------");
        System.out.println("Search History: ");

        // usage of lambda function
        m_searchHistory.forEach((key, value) -> System.out.println("search term: " + key + ", frequency: " + value.frequency
                + ", last searched: " + value.lastSearched.toString()));
        System.out.println("-----------------------------------");
    }

}

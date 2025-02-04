package bu.cs622.csv.search.engine.utility;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class SearchHistory {

    public static Map<String, Tuple> m_searchHistory = new HashMap<>();

    public static void insertRecord(String keyword){
        int frequency = 1;
        if (m_searchHistory.containsKey(keyword)) {
            frequency = (int) m_searchHistory.get(keyword).frequency + 1;
        }

        Tuple record = new Tuple(frequency, Instant.now());
        m_searchHistory.put(keyword, record);
    }

    public static Map<String, Tuple> getSearchHistory(){
        return m_searchHistory;
    }

    public static void printHistory(){
        System.out.println("-----------------------------------");
        System.out.println("Search History: ");

        // usage of lambda function
        m_searchHistory.forEach((key, value) -> System.out.println("search term: " + key + ", frequency: " + value.frequency
                + ", last searched: " + value.lastSearched.toString()));
        System.out.println("-----------------------------------");
    }

}

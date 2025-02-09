import bu.cs622.csv.search.engine.Processor;
import bu.cs622.csv.search.engine.utility.SearchHistory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class SearchHistoryTest {

    @Test
    public void testSearchHistory() {
        assertDoesNotThrow(() -> new SearchHistory());
        Instant now = Instant.now();
        String keyword = "Test Search History";
        SearchHistory.insertRecord(keyword);
        assert((int)SearchHistory.getSearchHistory().get(keyword).frequency == 1);

        SearchHistory.insertRecord(keyword);
        assert((int)SearchHistory.getSearchHistory().get(keyword).frequency == 2);

        SearchHistory.insertRecord(keyword);
        assert((int)SearchHistory.getSearchHistory().get(keyword).frequency == 3);

        Instant lastSearched = (Instant)SearchHistory.getSearchHistory().get(keyword).lastSearched;
        assert(lastSearched.isAfter(now));

        assertDoesNotThrow(() -> SearchHistory.printHistory()); // example of lambda function usage

    }

}

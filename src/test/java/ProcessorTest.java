import bu.cs622.csv.search.engine.Processor;
import bu.cs622.csv.search.engine.utility.SearchHistory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProcessorTest {
    private static String mockFile;
    private static String INPUT_PATH;
    private static String mockFilePath;
    private static Processor m_processor;


    @BeforeAll
    public static void setup() {
        ClassLoader classLoader = ProcessorTest.class.getClassLoader();
        INPUT_PATH = Paths.get(classLoader.getResource("inputs").getPath()).toString();
        mockFile = "mock_data.csv";
        mockFilePath = Paths.get(INPUT_PATH, mockFile).toString();
        m_processor = new Processor();
    }

    @Test
    public void testSearch() {

        String keyword = "tech";
        int result = m_processor.search(mockFilePath, keyword, false);
        assert (result == 2);

        keyword = "lifestyle";
        result = m_processor.search(mockFilePath, keyword, false);
        assert (result == 1);

        keyword = "reaz";
        result = m_processor.search(mockFilePath, keyword, false);
        assert (result == 0);
    }

    @Test
    public void testSearchCaseSensitive() {
        String keyword = "Tech";
        int result = m_processor.search(mockFilePath, keyword, true);
        assert (result == 1);

        keyword = "TECH";
        result = m_processor.search(mockFilePath, keyword, true);
        assert (result == 0);
    }

    @Test
    public void testCheckLengthThrowsException() throws NoSuchMethodException {
        // Access the private method using reflection
        Method checkLengthMethod = Processor.class.getDeclaredMethod("checkLength", String[].class);
        checkLengthMethod.setAccessible(true);

        String[] invalidRow = new String[]{"data1", "data2"};

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            try {
                checkLengthMethod.invoke(m_processor, (Object) invalidRow);
            } catch (InvocationTargetException e) {
                throw (RuntimeException) e.getCause(); // casts the thrown exception into a runtime exception
            }
        });

        assertEquals("OutputProcessor::checkLength invalid length for row, expected: 26 Found: 2 , data: [data1, data2]", thrown.getMessage());
    }

    @Test
    public void testSearchHistory() {
        String keyword = "Tech";
        m_processor.search(mockFilePath, keyword, false);
        assert((int)SearchHistory.getSearchHistory().get(keyword).frequency == 1);

        m_processor.search(mockFilePath, keyword, false);
        assert((int)SearchHistory.getSearchHistory().get(keyword).frequency == 2);

        m_processor.search(mockFilePath, keyword, false);
        assert((int)SearchHistory.getSearchHistory().get(keyword).frequency == 3);

    }

}

import bu.cs622.csv.search.engine.Processor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

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

}

import bu.cs622.csv.search.engine.FilesMerger;
import bu.cs622.csv.search.engine.utility.FileHandler;
import bu.cs622.csv.search.engine.utility.FileHandlingException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Name: Reaz W. Rahman
 * Course: CS 622: Advanced Programming Techniques
 * Date: 2/01/2025
 * File name: FilesMerger.java
 * Description: This class tests the m_fileHandler class.
 */

public class FilesMergerTest extends FilesMerger {

    @Test
    public void testMergeInputFilesWithException() throws IOException {
        FileHandler mockHandler = Mockito.mock(FileHandler.class);

        // Anonymous subclass to override getFileHandler()
        FilesMerger filesMerger = new FilesMerger() {
            @Override
            protected FileHandler getFileHandler() {
                return mockHandler;
            }
        };

        doThrow(new IOException("Failed to create file")).when(mockHandler).recreateFile(anyString());
        assertThrows(FileHandlingException.class, () -> filesMerger.mergeInputFiles());
    }

    @Test
    public void testMergeInputFiles() throws IOException, FileHandlingException {
        FileHandler mockHandler = Mockito.mock(FileHandler.class);
        List<String> mockList = Arrays.asList("f1", "f2", "f3", "f4", "f5");

        // Anonymous subclass to override getFileHandler()
        FilesMerger filesMerger = new FilesMerger() {
            @Override
            protected FileHandler getFileHandler() {
                return mockHandler;
            }
        };

        doNothing().when(mockHandler).recreateFile(anyString());
        doNothing().when(mockHandler).writeContent(anyString(),anyString());
        doReturn(mockList).when(mockHandler).getFilesList(anyString());
        doNothing().when(mockHandler).copyFile(anyString(), anyString(), anyBoolean());

        filesMerger.mergeInputFiles();
        verify(mockHandler, times(mockList.size())).copyFile(anyString(), anyString(), eq(true));

    }
}

package bu.cs622.csv.search.engine.utility;

/**
 * Name: Reaz W. Rahman
 * Course: CS 622: Advanced Programming Techniques
 * Date: 2/01/2025
 * File name: FileHandlingException.java
 * Description: This class creates a custom exception for FileHandler related errors
 */

public class FileHandlingException extends Exception{
    public FileHandlingException(String exception) {
        super(exception);
        String message = "FileHandlingException::FileHandlingException Ran into error while processing files, exception: " +
                exception;
        System.out.println(message);
    }
}

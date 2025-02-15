# Overview

This is a simple program that merges multiple csv files into a single csv file, reads the merged csv file and searches
for specific keywords.

Additionally, an in-memory map is implemented to store the frequency and the last time a keyword was searched.

Video Demo YouTube link Part 1: https://www.youtube.com/watch?v=QNO0AJfVxnQ 

Part 2 (in-memory map): https://www.youtube.com/watch?v=eYHxfFBRiv0 


## How to run

1) Clone the repo: `git clone https://github.com/reazwrahman/csv-search-engine.git`
2) Build the program: `mvn clean install`
3) Run unit tests and generate coverage report: `mvn clean test`
4) Coverage report will be generated in `target/site/jacoco/index.html`
5) Run the program from IntelliJ IDE, .idea is already configured.

## Program Flow

1) User is prompted to enter a keyword to search
2) Program reads all csv files from `src/main/resources/inputs` directory
3) Merges all csv files into a single csv file in the root directory: `output.csv`
4) Reads the merged csv file and searches for specific keywords

## Project Structure

### Business Logic

1) FileHandler.java: Handles file operations such as reading, writing, copying csv files
2) FileMerger.java: Merges multiple csv files into a single csv file
3) Processor.java: Reads the merged csv file and searches for specific keywords 
4) SearchHistory.java: In-memory map to store the frequency and the last time a keyword was searched
5) Main.java: Entry point of the program

### Unit Test

1) FileHandlerTest.java: Unit tests for FileHandler.java
2) ProcessorTest.java: Unit tests for Processor.java
3) FilesMergerTest.java: Unit tests for FilesMerger.java  
4) SearchHistoryTest.java: Unit tests for SearchHistory.java

      

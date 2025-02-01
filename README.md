# Overview 
This is a simple program that merges multiple csv files into a single csv file, reads the merged csv file and searches for specific keywords.

## How to run
1) Clone the repo: `git clone https://github.com/reazwrahman/csv-search-engine.git` 
2) Build the program: `mvn clean install` 
3) Run unit tests and generate coverage report: `mvn clean test`
    3.1) Coverage report will be generated in `target/site/jacoco/index.html`
4) Run the program from IntellIJ IDE, .ide is already configured.

## Program Flow
1) Program reads all csv files from `src/main/resources/inputs` directory
2) Merges all csv files into a single csv file in the root directory: `output.csv`
3) Reads the merged csv file and searches for specific keywords

## Project Structure 
### Business Logic
1) FileHandler.java: Handles file operations such as reading, writing, copying csv files 
2) FileMerger.java: Merges multiple csv files into a single csv file 
3) Processor.java: Reads the merged csv file and searches for specific keywords
4) Main.java: Entry point of the program 

### Test 
1) FileHandlerTest.java: Unit tests for FileHandler.java 
2) ProcessorTest.java: Unit tests for Processor.java

## TODO
4) video demo
    5) demo program running
    6) demo unit test + coverage
        5) talk about why opencsv
        6) why stream based processing, deprecated methods 
      

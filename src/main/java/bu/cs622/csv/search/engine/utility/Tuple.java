package bu.cs622.csv.search.engine.utility;

/**
 * Name: Reaz W. Rahman
 * Course: CS 622: Advanced Programming Techniques
 * Date: 2/03/2025
 * File name: Tuple.java
 * Description: This class is responsible for creating a Tuple object with two generic types,
 *              reusable across the project.
 */


public class Tuple <S, U> {
    public S frequency;
    public U lastSearched;

    public Tuple(S freq, U timestamp){
        frequency = freq;
        lastSearched = timestamp;
    }
}

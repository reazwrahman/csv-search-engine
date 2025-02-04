package bu.cs622.csv.search.engine.utility;

import java.time.Instant;

public class Tuple {
    public int frequency;
    public Instant lastSearched;

    public Tuple(int freq, Instant timestamp){
        frequency = freq;
        lastSearched = timestamp;
    }
}

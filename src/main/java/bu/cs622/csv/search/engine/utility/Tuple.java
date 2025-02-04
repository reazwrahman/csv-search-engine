package bu.cs622.csv.search.engine.utility;

import java.time.Instant;

public class Tuple <S, U> {
    public S frequency;
    public U lastSearched;

    public Tuple(S freq, U timestamp){
        frequency = freq;
        lastSearched = timestamp;
    }
}

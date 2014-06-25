package containers;

import java.util.Random;

/**
 * Created by IORI on 2014/6/25.
 */
public class Prediction {

    private static Random rand = new Random(47);
    private boolean shadow = rand.nextDouble() > 0.5;

    public String toString() {
        if(shadow)
            return "Six more weeks of Winter!";
        else
            return "Early Spring!";
    }
}

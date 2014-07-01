package enumerated;

import java.util.Random;

/**
 * Created by IORI on 2014/6/27.
 */
public class Enums {
    private static Random rand = new Random(47);

    public static <T> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}

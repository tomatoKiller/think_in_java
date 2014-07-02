package concurrency;

/**
 * Created by IORI on 2014/7/2.
 */
public class SynchronizedGenerator extends IntGenerator {
    private int currentValue = 0;

    @Override
    synchronized
    public int next() {
        ++currentValue;
        Thread.yield();
        ++currentValue;
        return currentValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedGenerator());
    }
}

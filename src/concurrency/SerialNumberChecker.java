package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IORI on 2014/7/4.
 */

class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;

    public CircularSet(int size) {
        array = new int[size];
        len = size;
        for (int i = 0; i < len; i++) {
            array[i] = -1;
        }
    }

    public synchronized void add(int i) {
        array[index] = i;
        index = ++index % len;
    }

    public synchronized boolean contains(int val) {
        for (int i = 0; i < len; i++) {
            if (array[i] == val) {
                return true;
            }
        }
        return false;
    }
}

public class SerialNumberChecker {
    public static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();
}

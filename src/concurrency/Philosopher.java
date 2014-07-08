package concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by IORI on 2014/7/8.
 */
public class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);

    private void pause() throws InterruptedException {
        if (ponderFactor == 0)
            return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " " + "thinking");
                pause();
                System.out.println(this + " " + "grabbing right");
                right.take();
                System.out.println(this + " " + "grabbing left");
                left.take();
                System.out.println(this + " " + "eating");
                pause();
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Philosopher " + id;
    }

    public Philosopher(int id, int ponderFactor, Chopstick right, Chopstick left) {
        this.id = id;
        this.ponderFactor = ponderFactor;
        this.right = right;
        this.left = left;
    }
}

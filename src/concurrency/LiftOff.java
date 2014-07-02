package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by IORI on 2014/7/1.
 */
public class LiftOff implements Runnable {

    protected int countdown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {}

    public LiftOff(int countdown) {
        this.countdown = countdown;
    }
    public String status() {
        return "#" + id + "(" + (countdown > 0 ? countdown : "liftoff") + "), ";
    }

    @Override
    public void run() {
        while (countdown-- > 0){
            System.out.println(status());
//            Thread.yield();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("Interrupted");
            }
        }

    }

}

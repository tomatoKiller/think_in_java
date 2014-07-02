package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by IORI on 2014/7/2.
 */
public class SimpleDaemons implements Runnable {


    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemons = new Thread(new SimpleDaemons());
            daemons.setDaemon(true);
            daemons.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}

package concurrency;

/**
 * Created by IORI on 2014/7/1.
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        Thread d = new Thread(new LiftOff());
        t.start();
//        d.start();
//        d.destroy();
        System.out.println("waiting for LiftOff");
        System.gc();


    }
}

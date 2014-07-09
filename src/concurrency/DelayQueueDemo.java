package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.TimeUnit.*;

class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;

    protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

    public DelayedTask(int Mill) {
        delta = Mill;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask)o;
        if (trigger > that.trigger)
            return 1;
        else if (trigger < that.trigger)
            return -1;
        return 0;
    }

    @Override
    public void run() {
        System.out.println(this + " ");
    }

    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task " + id;
    }

    public String  summary() {
        return "(" + id + ":" + delta + ")";
    }

}

class EndSentinel extends DelayedTask {
    private ExecutorService exec;

    EndSentinel(int Mill, ExecutorService exec) {
        super(Mill);
        this.exec = exec;
    }

    @Override
    public void run() {
        for (DelayedTask d : sequence) {
            System.out.print(d.summary() + " ");
        }
        System.out.println();
        System.out.println(this + " Calling shutdownNow()");
        exec.shutdownNow();
    }
}

class DelayedTaskConsumer implements Runnable {
    private DelayQueue<DelayedTask> q;

    DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted())
                q.take().run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}

public class DelayQueueDemo {
    public static void main(String[] args) {
        Random rand = new Random(47);
        DelayQueue dq = new DelayQueue();

        for (int i = 0; i < 20; i++) {
            dq.put(new DelayedTask(rand.nextInt(5000)));
        }

        ExecutorService exec = Executors.newCachedThreadPool();

        dq.put(new EndSentinel(5000, exec));

        exec.execute(new DelayedTaskConsumer(dq));
    }
}

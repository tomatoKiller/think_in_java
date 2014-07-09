package concurrency;

import generics.Generator;

import java.util.List;
import java.util.concurrent.*;

class ExchangerProducer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private T value;
    private List<T> holder;

    public ExchangerProducer(T v, Exchanger<List<T>> exchanger, List<T> holder) {
        this.exchanger = exchanger;
        this.holder = holder;
        this.value = v;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < ExchangerDemo.size; i++) {
                    holder.add(value);
                }
                holder = exchanger.exchange(holder);
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }
}

class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;

    public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                holder = exchanger.exchange(holder);
                for (T x : holder) {
                    value = x;
                    holder.remove(x);
                }
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
        System.out.println("Final value: " + value);
    }
}

public class ExchangerDemo {
    static int size = 10;
    static int delay = 5;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Integer>> xc = new Exchanger<List<Integer>>();

        List<Integer>
                producerList = new CopyOnWriteArrayList<Integer>(),
                comsumerList = new CopyOnWriteArrayList<Integer>();

        exec.execute(new ExchangerProducer<Integer>(4,xc,producerList));
        exec.execute(new ExchangerConsumer<Integer>(xc,comsumerList));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
}

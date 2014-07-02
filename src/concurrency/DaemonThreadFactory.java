package concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * Created by IORI on 2014/7/2.
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}

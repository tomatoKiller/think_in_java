package concurrency;

/**
 * Created by IORI on 2014/7/2.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel() {canceled = true;}
    public boolean isCanceled() { return canceled;}

}

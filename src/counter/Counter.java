package counter;

public class Counter implements Runnable {

    private volatile long count;

    public Counter(long initCount) {
        this.count = initCount;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1);
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }


    public synchronized void updateCounter(long millis) {
        count = millis;
    }

    public synchronized long getCount() {
        return count;
    }
}

package com.zpy.concurrency.stage2.countdown_11;

/**
 * 自己实现CountDownLatch
 */
public class MyCountDown {
    private int size;
    private int count = 0;

    public MyCountDown(int size) {
        this.size = size;
    }

    public void countDown() {
        synchronized (this) {
            this.count++;
            this.notifyAll();
        }
    }

    public void await() {
        synchronized (this) {
            while (count != size) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

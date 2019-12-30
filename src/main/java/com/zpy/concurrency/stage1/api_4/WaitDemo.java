package com.zpy.concurrency.stage1.api_4;

/**
 * notifyAll的顺序为LIFO。
 */
public class WaitDemo {
    public static void main(String[] args) {
        WaitDemo waitDemo = new WaitDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                waitDemo.waitMethod();
            }).start();
        }

        new Thread(() -> {
            waitDemo.notifyMethod();
        }).start();
    }

    public synchronized void waitMethod() {
        System.out.println(Thread.currentThread().getName() + " wait start");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " wait end");
    }

    public synchronized void notifyMethod() {
        notifyAll();
    }

}

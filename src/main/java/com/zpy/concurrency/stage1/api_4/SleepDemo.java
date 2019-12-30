package com.zpy.concurrency.stage1.api_4;

/**
 * sleep不释放锁，wait释放锁
 */
public class SleepDemo {
    public static void main(String[] args) {
        SleepDemo sleepDemo = new SleepDemo();
        for (int i = 0; i <5 ; i++) {
            new Thread(()->{
                sleepDemo.sleepMethod();
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <5 ; i++) {
            new Thread(()->{
                sleepDemo.waitMethod();
            }).start();
        }
    }

    public synchronized void sleepMethod() {
        System.out.println(Thread.currentThread().getName()+" sleep start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" sleep end");
    }

    public synchronized void waitMethod() {
        System.out.println(Thread.currentThread().getName()+" wait start");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" wait end");
    }

}

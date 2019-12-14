package com.zpy.concurrency.stage1.api_4;

public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10_100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10_100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");


        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10_100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        long end = System.currentTimeMillis();
        System.out.println("finish task,used " + (end - start) + "ms");
    }
}

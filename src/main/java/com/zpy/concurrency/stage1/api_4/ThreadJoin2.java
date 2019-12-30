package com.zpy.concurrency.stage1.api_4;

/**
 * 如果join()方法在一个线程实例上调用，
 当前运行着的线程将阻塞直到这个线程执行完成了
 */
public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" start");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+" end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" start");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+" end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");


        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" start");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+" end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
        //main线程等t1,t2,t3线程执行完了，才执行。
        t1.join();
        t2.join();
        t3.join();

        long end = System.currentTimeMillis();
        System.out.println("finish task,used " + (end - start) + "ms");
    }
}

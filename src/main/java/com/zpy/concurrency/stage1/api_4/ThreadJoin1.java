package com.zpy.concurrency.stage1.api_4;

public class ThreadJoin1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread t3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++) {
                            System.out.println(Thread.currentThread().getName() + ":index=" + i);
                        }
                    }
                }, "t3");
                t3.start();
                try {
                    t3.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ":index=" + i);
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ":index=" + i);
                }
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();     //等待join的线程执行完，main线程才会执行，但是多个join的线程顺序不确定。
        t2.join();

        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + ":index=" + i);
        }

    }
}


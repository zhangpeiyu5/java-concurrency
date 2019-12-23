package com.zpy.concurrency.stage2.threadlocalPattern_10;

public class ThreadLocalSimpleTest {
    private static ThreadLocalSimulator<String> threadLocal = new ThreadLocalSimulator<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread("t1") {
            @Override
            public void run() {
                threadLocal.set("threadlocal1");
                String s = threadLocal.get();
                System.out.println(Thread.currentThread().getName() + "->" + threadLocal.get());
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run () {
                threadLocal.set("threadlocal2");
                String s = threadLocal.get();
                System.out.println(Thread.currentThread().getName() + "->" + threadLocal.get());
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("------------------");
        System.out.println(Thread.currentThread().getName() + "->" + threadLocal.get());
    }
}

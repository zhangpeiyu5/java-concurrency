package com.zpy.concurrency.basic.threadconstructor_3;

public class ThreadCreate {
    private static int count = 0;

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    add(count);
                } catch (Error e) {
                    System.out.println("count=" + count);   //测试stackSize
                }
            }

            public void add(int i) {
                count++;
                add(i + 1);
            }
        };
        Thread t = new Thread(null, runnable, "test", 10000);
        t.start();


        //threadGroup
//        System.out.println(t.getThreadGroup());
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getThreadGroup());


        //获取threadGroup下运行的线程数
//        ThreadGroup tp = t.getThreadGroup();
//        System.out.println(tp.activeCount());

        //获取threadGroup下运行的线程
//        Thread[] list = new Thread[tp.activeCount()];
//        tp.enumerate(list);
//        System.out.println(Arrays.toString(list));

    }
}

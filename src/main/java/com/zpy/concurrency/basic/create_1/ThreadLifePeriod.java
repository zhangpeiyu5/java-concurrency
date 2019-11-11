package com.zpy.concurrency.basic.create_1;

public class ThreadLifePeriod {
    public static void main(String[] args) {
        Thread t = new Thread("Thread1") {
            @Override
            public void run() {
                print("start");
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000 * 2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                print("end");
            }
        };
        t.start();   //注意：start()方法不能调用多次，会报java.lang.IllegalThreadStateException
//        t.start();
        t.run();

    }


    public static void print(String s) {
        System.out.println(s);
    }
}

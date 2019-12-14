package com.zpy.concurrency.stage1.api_4;

/**
 * 中断interrupt方法
 */
public class ThreadInterrupt3_Join {
    private static Object object = new Object();
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

        Thread main = Thread.currentThread();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        main.interrupt();     //中断join方法
                    } catch (Exception e) {
                        System.out.println("t1 Exception");
                    }
                }
            }
        }, "t1");

        t1.start();
        System.out.println(main.isInterrupted());
        try {
            t1.join();
        } catch (Exception e) {
            System.out.println("main Exception");
            flag = false;
        }

        System.out.println(main.isInterrupted());


    }
}

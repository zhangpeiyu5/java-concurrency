package com.zpy.concurrency.stage1.api_4;

/**
 * 中断wait方法
 */
public class ThreadInterrupt2_Wait {
    private static Object object = new Object();
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        synchronized (object) {
                            object.wait();     //中断wait()
                        }

                    } catch (Exception e) {
                        System.out.println("t1 Exception");
                        flag = false;
                    }
                }
            }
        }, "t1");

        t1.start();
        System.out.println(t1.isInterrupted());
        t1.interrupt();


//        t1.stop();     //该方法失效
        System.out.println(t1.isInterrupted());


    }
}

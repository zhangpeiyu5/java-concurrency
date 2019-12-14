package com.zpy.concurrency.stage1.api_4;

/**
 * 中断sleep方法
 */
public class ThreadInterrupt1_Sleep {
    private static Object object = new Object();
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(1_1000);    //中断sleep()
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

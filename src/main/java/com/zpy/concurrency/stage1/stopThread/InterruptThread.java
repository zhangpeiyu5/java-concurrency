package com.zpy.concurrency.stage1.stopThread;

public class InterruptThread extends Thread {
    private static volatile boolean flag = false;   //设置结束标志

    public static void main(String[] args) throws InterruptedException {
        InterruptThread thread = new InterruptThread();
        thread.start();

        //主线程休眠3s
        System.out.println("主线程休眠3s");
        Thread.sleep(3000);

        System.out.println("设置接收标志");
        flag = true;

        System.out.println("设置中断");
        thread.interrupt();

        System.out.println("主线程退出");
    }

    @Override
    public void run() {
        while (!flag) {
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                System.out.println("InterruptThread线程被中断");
            }
        }
        System.out.println("InterruptThread线程退出");
    }
}

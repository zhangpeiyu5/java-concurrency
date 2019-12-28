package com.zpy.concurrency.stage1.stopThread;

public class FlagThread extends Thread {
    private static volatile boolean flag = false;   //设置结束标志

    public static void main(String[] args) throws InterruptedException {
        FlagThread flagThread = new FlagThread();
        flagThread.start();

        try {
            //主线程休眠3s
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("设置接收标志");
        flag = true;

        flagThread.join();
        System.out.println("主线程退出");
    }

    @Override
    public void run() {
        while (!flag) ;
        System.out.println("FlagThread线程结束");
    }
}

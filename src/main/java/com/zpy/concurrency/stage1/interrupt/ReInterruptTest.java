package com.zpy.concurrency.stage1.interrupt;

public class ReInterruptTest extends Thread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new ReInterruptTest();
        System.out.println("starting thread");
        thread.start();

        //主线程休眠3s
        Thread.sleep(3000);

        System.out.println("asking thread to interrupt");
        thread.interrupt();

        //主线程休眠3s
        Thread.sleep(3000);

        System.out.println("stopping");


    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                System.out.println(this.isInterrupted());   //捕获异常之后，中断标识被清除
                Thread.currentThread().interrupt();   //需要再次中断
            }
        }
        System.out.println("reInterrupt:" + this.isInterrupted());
    }
}

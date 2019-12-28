package com.zpy.concurrency.stage1.interrupt;

public class InteruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.currentThread();
        try {
            thread.interrupt();
            System.out.println(thread.isInterrupted());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("处理一些清理工作");
            System.out.println(thread.isInterrupted());   //捕获异常之后，中断标识被清除
            e.printStackTrace();
            throw e;
        }
    }
}

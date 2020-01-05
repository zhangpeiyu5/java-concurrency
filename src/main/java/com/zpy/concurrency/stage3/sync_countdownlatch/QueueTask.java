package com.zpy.concurrency.stage3.sync_countdownlatch;

import jdk.nashorn.internal.ir.ReturnNode;

import java.util.concurrent.CountDownLatch;

/**
 * 排队线程
 */
public class QueueTask implements Runnable {
    private CountDownLatch countDownLatch;

    public QueueTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("开始排队，进入队列等待");
        try {
            Thread.sleep(2000);
            System.out.println("排队结束，可以开始交费");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }
}

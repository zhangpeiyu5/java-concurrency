package com.zpy.concurrency.stage3.sync_countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 看大夫线程
 */
public class SeeDoctorThread implements Runnable {
    private CountDownLatch countDownLatch;

    public SeeDoctorThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("开始看大夫，大夫开始诊脉");
        try {
            Thread.sleep(3000);
            System.out.println("看大夫结束，大夫开药方子");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }
}

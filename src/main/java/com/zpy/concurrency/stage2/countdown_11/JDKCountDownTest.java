package com.zpy.concurrency.stage2.countdown_11;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 场景：多线程处理多个任务，等所有线程第一阶段都执行完时，开启第二阶段。
 * 使用CountDownLatch
 */
public class JDKCountDownTest {
    private static Random random = new Random(System.currentTimeMillis());
    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {
        //多线程处理第一阶段任务
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is working..");
                    Thread.sleep(random.nextInt(1000));
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
        countDownLatch.await();
        //第一阶段任务结束，开始第二阶段任务
        System.out.println("开始第二阶段任务");
        System.out.println("-----");
        System.out.println("第二阶段任务结束");
    }
}

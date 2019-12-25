package com.zpy.concurrency.stage2.countdown_11;

import java.util.Random;

public class MyCountDownTest {
    private static Random random = new Random(System.currentTimeMillis());
    private static MyCountDown myCountDown = new MyCountDown(5);

    public static void main(String[] args) throws InterruptedException {
        //多线程处理第一阶段任务
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is working..");
                    Thread.sleep(random.nextInt(1000));
                    myCountDown.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
        myCountDown.await();
        //第一阶段任务结束，开始第二阶段任务
        System.out.println("开始第二阶段任务");
        System.out.println("-----");
        System.out.println("第二阶段任务结束");
    }
}

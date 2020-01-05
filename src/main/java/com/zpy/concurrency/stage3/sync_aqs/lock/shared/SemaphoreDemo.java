package com.zpy.concurrency.stage3.sync_aqs.lock.shared;

import java.util.concurrent.Semaphore;

/**
 * 信号量——AQS在共享模式下的一种实现。
 * Semaphore还用于Hystrix限流框架中，控制系统并发在可控的范围内，保证系统高可用。
 */
public class SemaphoreDemo {
    /**
     * 限定线程数量
     */
    private static final Semaphore SEMAPHORE = new Semaphore(3);

    /**
     * 每次获取的许可数
     */
    private static final int PERMITS = 2;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new TestThread(), "thread" + i).start();
        }

    }


    private static class TestThread extends Thread {

        @Override
        public void run() {
            try {
                //获取许可
                SEMAPHORE.acquire(PERMITS);
                System.out.println(Thread.currentThread().getName() + "进来了");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //一定要释放许可！！
                SEMAPHORE.release(PERMITS);
            }
        }
    }
}

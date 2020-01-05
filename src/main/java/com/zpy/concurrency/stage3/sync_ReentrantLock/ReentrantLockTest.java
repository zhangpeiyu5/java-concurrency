package com.zpy.concurrency.stage3.sync_ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock公平锁/非公平锁: 用与提供线程安全。
 */
public class ReentrantLockTest {
    private static Lock lock = new ReentrantLock(true);  //修改为false非公平锁，查看输出。

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadDemo(i)).start();
        }
    }


    private static class ThreadDemo extends Thread {
        private Integer id;

        public ThreadDemo(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(200);

                for (int i = 0; i < 2; i++) {
                    lock.lock();
                    System.out.println("获得锁的线程是：" + id);
                    lock.unlock();  //释放锁。 对比非公平锁和公平锁下的输出。
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

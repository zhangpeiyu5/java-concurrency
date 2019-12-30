package com.zpy.concurrency.stage1.synchronize_4.deadLock_3;

/**
 * 面试题：死锁代码示例
 */
public class DeadLock {
    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (A) {
                System.out.println(Thread.currentThread().getName() + "获取到锁A");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println(Thread.currentThread().getName() + "获取到锁B");
                }
            }
        }).start();


        new Thread(() -> {
            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + "获取到锁B");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println(Thread.currentThread().getName() + "获取到锁A");
                }
            }
        }).start();

    }
}

package com.zpy.concurrency.stage1.inter_thread_communicate_5.wait_sleep;

import java.util.stream.Stream;

/**
 * wait()和sleep()的方法
 * 1）wait是Object的方法；sleep是Thread的方法；
 * 2）使用wait的时候必须先获取到锁；
 * 3）sleep不会释放锁，wait会释放锁；
 * 4）sleep方法不需要被唤醒，wait方法需要被唤醒。
 */
public class WaitVSsleep {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Stream.of("t1", "t2").forEach(n -> {
            new Thread() {
                @Override
                public void run() {
                    m2();
                }
            }.start();
        });

    }

    public static void m1() {
        synchronized (LOCK) {
            System.out.println(Thread.currentThread().getName() + "->enter...");
            try {
                Thread.sleep(3000);   //等thread0执行完,thread2才执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m2() {
        synchronized (LOCK) {
            System.out.println(Thread.currentThread().getName() + "->enter...");
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

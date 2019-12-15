package com.zpy.concurrency.stage2.waitset_2;

import java.util.stream.IntStream;

/**
 * 对wait更深入的理解：
 * 1）所有的对象都会有一个wait set，用来存放调用了该对象wait方法之后进入block状态的线程；
 * 2）wait的线程被notify之后，不一定立即得到执行，需要先得到锁；
 * 3）线程从wait set中被唤醒的顺序不一定是FIFO；
 * 4）线程被唤醒后必须重新获取锁，但是不从synchronized所在的地方开始。
 */
public class WaitSet {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    new Thread(String.valueOf(i)) {
                        @Override
                        public void run() {
                            synchronized (LOCK) {
                                try {
                                    System.out.println(Thread.currentThread().getName() + " will come to wait");
                                    LOCK.wait();  //会将当前线程放到LOCK对象的wait set中（所有对象都有一个wait set）,并且释放锁。
                                    System.out.println(Thread.currentThread().getName() + " will leave from wait");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                });

        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    new Thread(String.valueOf(i)) {
                        @Override
                        public void run() {
                            synchronized (LOCK) {
                                try {
                                    LOCK.notify();
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                });
    }
}

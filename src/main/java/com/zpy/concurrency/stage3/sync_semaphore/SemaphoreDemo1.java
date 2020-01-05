package com.zpy.concurrency.stage3.sync_semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量的作用：控制并发。
 */
public class SemaphoreDemo1 {
    //信号量大小
    private static final int SEM_SIZE = 10;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(SEM_SIZE);
        Thread myThread1 = new MyThread("线程1", semaphore);
        Thread myThread2 = new MyThread("线程2", semaphore);
        myThread1.start();
        myThread2.start();

        int acquire = 5;
        System.out.println(Thread.currentThread().getName() + " trying to acquire");
        try {
            semaphore.acquire(acquire);
            System.out.println(Thread.currentThread().getName() + " acquire successfully");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(acquire);
            System.out.println(Thread.currentThread().getName() + " release successfully");
        }

    }

    private static class MyThread extends Thread {
        private Semaphore semaphore;

        public MyThread(String name, Semaphore semaphore) {
            super(name);
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            int count = 5;
            System.out.println(Thread.currentThread().getName() + " trying to acquire");
            try {
                semaphore.acquire(count);
                System.out.println(Thread.currentThread().getName() + " acquire successfully");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(count);
                System.out.println(Thread.currentThread().getName() + " release successfully");
            }
        }
    }
}

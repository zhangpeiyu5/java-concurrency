package com.zpy.concurrency.stage3.sync_aqs.condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition的使用demo
 */
public class ConditionDemo {
    private ReentrantLock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();

    private Condition condition2 = lock.newCondition();

    private Condition condition3 = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo conditionDemo = new ConditionDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        TestThread1 thread1 = conditionDemo.new TestThread1();
        TestThread2 thread2 = conditionDemo.new TestThread2();
        TestThread3 thread3 = conditionDemo.new TestThread3();
        // 启动线程任务1、2、3.
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread3);
        Thread.sleep(2000);

        // 依次唤醒各线程任务.
        signalTest(conditionDemo);

        //关闭线程池
        executorService.shutdown();

    }

    public static void signalTest(ConditionDemo conditionDemo) throws InterruptedException {
        // 获取独占锁 唤醒condition1的线程
        conditionDemo.lock.lock();
        conditionDemo.condition1.signal();
        // 释放独占锁 等待thread1执行完毕.
        conditionDemo.lock.unlock();

        // 获取独占锁 唤醒condition2的线程
        conditionDemo.lock.lock();
        conditionDemo.condition2.signal();
        // 释放独占锁 等待thread2执行完毕.
        conditionDemo.lock.unlock();

        // 获取独占锁 唤醒condition3的线程
        conditionDemo.lock.lock();
        conditionDemo.condition3.signal();
        // 释放独占锁 等待thread2执行完毕.
        conditionDemo.lock.unlock();
    }

    private class TestThread1 extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程启动");
            lock.lock();
            try {
                condition1.await();
                System.out.println(Thread.currentThread().getName() + "被唤醒");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    private class TestThread2 extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程启动");
            lock.lock();
            try {
                condition2.await();
                System.out.println(Thread.currentThread().getName() + "被唤醒");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }


    private class TestThread3 extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程启动");
            lock.lock();
            try {
                condition3.await();
                System.out.println(Thread.currentThread().getName() + "被唤醒");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}

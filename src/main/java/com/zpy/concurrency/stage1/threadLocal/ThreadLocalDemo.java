package com.zpy.concurrency.stage1.threadLocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * ThreadLocal使用示例
 */
public class ThreadLocalDemo {
    /**
     * 定义ThreadLocal<Integer>对象，并且设置初始值为3。
     */
    private ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 3);

    /**
     * 设置一个信号量，许可数为1，让三个线程顺序执行。
     */
    private Semaphore semaphore = new Semaphore(1);

    private Random random = new Random();

    public class Worker implements Runnable {

        @Override
        public void run() {
            try {
                //休眠任意1s内时间
                Thread.sleep(random.nextInt(1000));

                //获取许可
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //从threadLocal中获取值
            int value = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + "threadlocal  old value=" + value);

            //修改threadLocal的值
            int newValue = random.nextInt(1000);
            System.out.println(Thread.currentThread().getName() + "threadlocal  new value=" + newValue);
            threadLocal.set(newValue);
            System.out.println(Thread.currentThread().getName() + "threadlocal  lastest value=" + threadLocal.get());

            //释放信号量
            semaphore.release();

            /**
             * 在线程池中，当线程退出之前一定要记得调用remove方法，因为在线程池中的线程对象执行完后不被回收，是循环使用的。
             * Thread有个强引用指向ThreadLocalMap,ThreadLocalMap有强引用指向Entry,Entry的key是弱引用的ThreadLocal对象。
             * 如果ThreadLocal使用一次后就不在有任何引用指向它，JVM GC 会将ThreadLocal对象回收掉。导致Entry变为{null : value}。
             * 此时这个Entry已经无效，因为key被回收了，而value无法被回收，一直存在内存中。会引起内存泄漏。
             */
            threadLocal.remove();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        es.execute(threadLocalDemo.new Worker());
        es.execute(threadLocalDemo.new Worker());
        es.execute(threadLocalDemo.new Worker());
        es.shutdown();
    }

}

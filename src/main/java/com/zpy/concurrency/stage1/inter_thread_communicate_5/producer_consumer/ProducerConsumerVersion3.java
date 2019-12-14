package com.zpy.concurrency.stage1.inter_thread_communicate_5.producer_consumer;

/**
 * 线程间通过wait()、notifyAll()进行数据通信
 * 多个生产者、多个消费者的情况不会发生假死。
 */
public class ProducerConsumerVersion3 {
    private int i;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    public static void main(String[] args) {
        ProducerConsumerVersion3 pc3 = new ProducerConsumerVersion3();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc3.produce("p1");
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc3.produce("p2");
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc3.consume("c1");
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc3.consume("c2");
                }
            }
        }.start();

    }

    public void produce(String threadName) {
        synchronized (LOCK) {
            if (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println(threadName + ":" + i);
                isProduced = true;
                LOCK.notifyAll();
            }
        }
    }

    public void consume(String threadName) {
        synchronized (LOCK) {
            if (isProduced) {
                System.out.println(threadName + ":" + i);
                isProduced = false;
                LOCK.notifyAll();
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

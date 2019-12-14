package com.zpy.concurrency.stage1.inter_thread_communicate_5.producer_consumer;

/**
 * 线程间通过wait()、notify()进行数据通信
 * 一个生产者、一个消费者的情况没问题
 * 多个生产者、多个消费者的情况会发生全部wait()假死的现象
 */
public class ProducerConsumerVersion2 {
    private int i;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    public static void main(String[] args) {
        ProducerConsumerVersion2 pc2 = new ProducerConsumerVersion2();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc2.produce();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc2.consume();
                }
            }
        }.start();

    }

    public void produce() {
        synchronized (LOCK) {
            if (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("p:" + i);
                isProduced = true;
                LOCK.notify();
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProduced) {
                System.out.println("c:" + i);
                isProduced = false;
                LOCK.notify();
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

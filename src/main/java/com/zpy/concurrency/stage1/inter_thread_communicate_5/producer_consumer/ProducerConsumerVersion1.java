package com.zpy.concurrency.stage1.inter_thread_communicate_5.producer_consumer;

/**
 * 线程间无数据通信
 *
 */
public class ProducerConsumerVersion1 {
    private int i;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    public static void main(String[] args) {
        ProducerConsumerVersion1 pc1 = new ProducerConsumerVersion1();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc1.produce();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc1.consume();
                }
            }
        }.start();

    }

    public void produce() {
        synchronized (LOCK) {
            i++;
            System.out.println("p:" + i);
        }
    }

    public void consume() {
        synchronized (LOCK) {
            System.out.println("c:" + i);
        }

    }
}

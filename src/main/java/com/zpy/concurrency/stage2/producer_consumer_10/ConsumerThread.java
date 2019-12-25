package com.zpy.concurrency.stage2.producer_consumer_10;

import java.util.concurrent.atomic.AtomicInteger;

public class ConsumerThread extends Thread {

    private final MessageQueue messageQueue;
    private final AtomicInteger count;

    public ConsumerThread(MessageQueue messageQueue, AtomicInteger count) {
        this.messageQueue = messageQueue;
        this.count = count;
    }

    @Override
    public void run() {
        while (true) {
            messageQueue.get();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

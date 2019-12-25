package com.zpy.concurrency.stage2.producer_consumer_10;

import java.util.concurrent.atomic.AtomicInteger;

public class ProducerThread extends Thread {

    private final MessageQueue messageQueue;

    private AtomicInteger count;

    public ProducerThread(MessageQueue messageQueue, AtomicInteger count) {
        this.messageQueue = messageQueue;
        this.count = count;
    }

    @Override
    public void run() {
        while (true) {
            Message message = new Message("message-" + count.getAndIncrement());
            messageQueue.put(message);
            System.out.println(Thread.currentThread().getName() + " produce:" + message.getData());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.zpy.concurrency.stage2.producer_consumer_9;

import java.util.concurrent.atomic.AtomicInteger;

public class ProduceAndConsumeClient {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        AtomicInteger count=new AtomicInteger(0);
        new ProducerThread(messageQueue,count).start();
        new ProducerThread(messageQueue,count).start();
        new ConsumerThread(messageQueue,count).start();
        new ConsumerThread(messageQueue,count).start();
        new ConsumerThread(messageQueue,count).start();
    }
}

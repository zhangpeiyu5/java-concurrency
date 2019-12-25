package com.zpy.concurrency.stage2.producer_consumer_10;

import java.util.LinkedList;

public class MessageQueue {
    private final LinkedList<Message> messageQueue;
    private final int QUEUE_SIZE_LIMIT = 100;

    public MessageQueue() {
        this.messageQueue = new LinkedList<>();
    }

    public void put(Message message) {
        synchronized (messageQueue) {
            if (messageQueue.size() > 100) {
                try {
                    messageQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                messageQueue.add(message);
                messageQueue.notifyAll();
            }
        }
    }


    public void get() {
        synchronized (messageQueue) {
            if (messageQueue.size() <= 0) {
                try {
                    messageQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Message message = messageQueue.removeFirst();
                messageQueue.notifyAll();
                System.out.println(Thread.currentThread().getName() + " consume:" + message.getData());
            }
        }
    }
}


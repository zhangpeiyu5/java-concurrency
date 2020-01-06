package com.zpy.concurrency.stage3.blockingQueue.linkedBlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue:一个由链表结构组成的无界阻塞队列。
 */
public class LinkedBlockingQueueTest {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }

        System.out.println("queue size=" + queue.size());
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.poll());
        }

        System.out.println("queue size=" + queue.size());
    }
}

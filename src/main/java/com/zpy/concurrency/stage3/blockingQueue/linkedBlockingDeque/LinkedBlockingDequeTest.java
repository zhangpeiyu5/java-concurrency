package com.zpy.concurrency.stage3.blockingQueue.linkedBlockingDeque;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * LinkedBlockingDeque:是一个由链表组成的双向阻塞队列。双向队列就意味着可以从对头、对尾两端插入和移除元素，同样意味着LinkedBlockingDeque支持FIFO、FILO两种操作方式。
 */
public class LinkedBlockingDequeTest {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<Integer> deque = new LinkedBlockingDeque<>();
        deque.putFirst(1);
        deque.addFirst(2);
        deque.add(3);

        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
        System.out.println("deque size=" + deque.size());
    }
}

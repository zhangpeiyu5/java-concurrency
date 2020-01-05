package com.zpy.concurrency.stage3.blockingQueue.arrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * BlockingQueue阻塞队列的应用场景：在线程池中，如果运行线程数大于核心线程数，会尝试把新加入的线程放入阻塞队列中，如果阻塞队列也满了，会扩容到
 * 最大线程数，如果还是不够，会执行拒绝策略。
 * <p>
 * ArrayBlockingQueue：是数组实现的线程安全的有界阻塞队列。
 * 1）线程安全：内部通过ReentrantLock互斥锁保护资源竞争；
 * 2）有界：ArrayBlockingQueue对应的数据是有界的；
 * 3）阻塞队列：多线程访问竞争资源时，当竞争资源已经被某个线程获取时，其他要获取该竞争资源的线程要阻塞等待。并且采用先进先出的方式。
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        System.out.println("增加值之前" + arrayBlockingQueue.size());
        for (int i = 0; i < 5; i++) {
            arrayBlockingQueue.add(i + "");
        }
        System.out.println("增加值之后" + arrayBlockingQueue.size());
        System.out.println(arrayBlockingQueue.toString());

        System.out.println("取值开始：");
        for (int i = 0; i < 5; i++) {
            System.out.println("取出的值为：" + arrayBlockingQueue.poll());
        }
    }
}

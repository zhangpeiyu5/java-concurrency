package com.zpy.concurrency.stage3.blockingQueue.deleyQueue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列：是一个支持延迟获取元素的无界阻塞队列。队列中的元素必须实现Delayed接口，在创建元素时指定多久才能从队列中获取当前元素。
 * 只有在延时期满时才能从队列中获取该元素。
 * <p>
 * 应用场景：
 * 1）缓存系统的设计：使用DelayQueue保存延时元素的有效期，使用一个线程循环查询DelayQueue，一旦存DelayQueue中获取到元素，就说明缓存到期了。
 * 2）定时任务调度。
 */
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Message item1 = new Message("消息1", 5, TimeUnit.SECONDS);
        Message item2 = new Message("消息2", 10, TimeUnit.SECONDS);
        Message item3 = new Message("消息3", 15, TimeUnit.SECONDS);

        DelayQueue<Message> delayQueue = new DelayQueue<>();
        delayQueue.add(item1);
        delayQueue.add(item2);
        delayQueue.add(item3);
        System.out.println(printDate() + " 开始");
        int queueSize = delayQueue.size();
        for (int i = 0; i < queueSize; i++) {
            Message take = delayQueue.take();
            System.out.format(printDate() + " 消息出队，属性name=%s%n", take.getName());
        }
        System.out.println(printDate() + " 结束");
    }

    static String printDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}

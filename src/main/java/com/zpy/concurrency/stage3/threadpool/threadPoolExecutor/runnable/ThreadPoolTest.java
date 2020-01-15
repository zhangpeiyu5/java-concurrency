package com.zpy.concurrency.stage3.threadpool.threadPoolExecutor.runnable;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 无返回值的ThreadPoolExecutor线程池Demo
 *
 *
 * ---------------线程池参数设置--------------：
 * 需要根据几个值来决定：
     tasks ：每秒的任务数，假设为500~1000
     taskcost：每个任务花费时间，假设为0.1s
     responsetime：系统允许容忍的最大响应时间，假设为1s

    1）核心线程数corePoolSize = 每秒需要多少个线程处理？
       threadcount = tasks/(1/taskcost) =tasks*taskcout =  (500~1000)*0.1 = 50~100 个线程。corePoolSize设置应该大于50
       根据8020原则，如果80%的每秒任务数小于800，那么corePoolSize设置为80即可
    2）阻塞队列：queueCapacity = (coreSizePool/taskcost)*responsetime
       计算可得 queueCapacity = 80/0.1*1 = 80。意思是队列里的线程可以等待1s，超过了的需要新开线程来执行
       切记不能设置为Integer.MAX_VALUE，这样队列会很大，线程数只会保持在corePoolSize大小，当任务陡增时，不能新开线程来执行，响应时间会随之陡增。
    3）最大线程数：maxPoolSize = (max(tasks)- queueCapacity)/(1/taskcost)
       计算可得 maxPoolSize = (1000-80)/10 = 92
      （最大任务数-队列容量）/每个线程每秒处理能力 = 最大线程数


 -------------------线程数设置--------------------
    CPU密集型：最佳线程数=CPU核数+1 （如：8核CPU：只要8个活动线程就能让8个CPU饱和，再多也没用了，因为CPU资源已经耗光了。加1是为了防止由于其他因素导致线程阻塞等。）
    IO密集型： 最佳线程数= （（线程等待时间+线程CPU时间）/线程CPU时间 ）* CPU核数   （如：8核CPU：密集计算所占的比重为0.5，则需要16个线程，线程有一半等待在IO上，有一半在耗CPU）
 *
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //核心线程数
        int corePoolSize = 2;
        //最大线程数
        int maxPoolSize = 4;
        //线程最大空闲时间
        long keepAliveTime = 10;
        //时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        //阻塞队列
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(2);
        //线程创建工厂
        ThreadFactory threadFactory = new MyThreadFactory();
        //线程池拒绝策略
        RejectedExecutionHandler rejectHandler = new MyRejectedExecutionHandler();
        ThreadPoolExecutor executor = null;
        try {
        /* 推荐的创建线程池的方式 */
        /* 不推荐使用现成的API创建线程池 */
            executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue, threadFactory, rejectHandler);
            //预启动所有核心线程  提升效率
            executor.prestartAllCoreThreads();
            //任务数量
            int count = 10;
            for (int i = 0; i < count; i++) {
                executor.submit(new RunnableTask(String.valueOf(i)));
            }
        } finally {
            //关闭线程池
            if (executor != null) {
                executor.shutdown();
            }
        }

    }

    public static class MyThreadFactory implements ThreadFactory {
        private final AtomicInteger threadId = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "线程" + threadId.getAndIncrement());
            System.out.println(thread.getName() + "已经被创建");
            return thread;
        }
    }

    public static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r.toString() + " rejected");
        }
    }

    public static class RunnableTask implements Runnable {
        private String name;

        public RunnableTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("线程" + name + " is running...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "RunnableTask-" + name;
        }
    }



    public static String printDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}

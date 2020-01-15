package com.zpy.concurrency.stage3.threadpool.threadPoolExecutor.runnable;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 无返回值的ThreadPoolExecutor线程池Demo
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

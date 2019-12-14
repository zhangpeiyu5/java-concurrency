package com.zpy.concurrency.stage1.threadpool_8;

public class SimpleThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        //创建线程池
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();

        //提交任务
        for (int i = 0; i < 40; i++) {
            simpleThreadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "  start");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "  end");
            });
        }

        Thread.sleep(3000);

        //关闭线程池
        simpleThreadPool.shutdown();

        //关闭线程池后提交任务
//        simpleThreadPool.submit(() -> {
//            System.out.println("i am new task");
//        });

    }
}

package com.zpy.concurrency.stage3.sync_countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch
 * 看病和排队两件事完成时，才能回家。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            executorService.execute(new QueueTask(countDownLatch));
            executorService.execute(new SeeDoctorThread(countDownLatch));
            // 等待其他线程完成各自的工作后再执行
            countDownLatch.await();
            System.out.println(printDate() + " 搞定，回家！！！总共耗时:" + (System.currentTimeMillis() - now));
        } finally {
            executorService.shutdown();
        }
    }

    public static String printDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date()) + " ";
    }
}

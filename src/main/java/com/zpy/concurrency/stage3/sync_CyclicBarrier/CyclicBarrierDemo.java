package com.zpy.concurrency.stage3.sync_CyclicBarrier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 栅栏
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //定义拦截的线程数为3，所有线程都到达栅栏时执行的任务TourGuideTask。
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new TourGuideTask());

        // 推荐使用ThreadPoolExecutor创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            executor.execute(new TravelTask(cyclicBarrier, "张三", 3));
            executor.execute(new TravelTask(cyclicBarrier, "李四", 5));
            executor.execute(new TravelTask(cyclicBarrier, "王五", 1));
        } finally {
            executor.shutdown();
        }
    }

    static String printDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}

package com.zpy.concurrency.stage3.sync_CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class TravelTask implements Runnable {
    /**
     * 栅栏
     */
    private CyclicBarrier cyclicBarrier;
    /**
     * 姓名
     */
    private String name;
    /**
     * 赶到的时间
     */
    private int arriveTime;

    public TravelTask(CyclicBarrier cyclicBarrier, String name, int arriveTime) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
        this.arriveTime = arriveTime;
    }

    @Override
    public void run() {
        try {
            //模拟到达需要花的时间
            Thread.sleep(arriveTime * 1000);
            System.out.println(CyclicBarrierDemo.printDate() + " " + name + "到达集合点");
            cyclicBarrier.await();
            System.out.println(CyclicBarrierDemo.printDate() + " " + name + "开始旅行啦~~");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.zpy.concurrency.stage3.sync_CyclicBarrier;

public class TourGuideTask implements Runnable {

    @Override
    public void run() {
        System.out.println(CyclicBarrierDemo.printDate() + " " + "****导游分发护照签证****");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

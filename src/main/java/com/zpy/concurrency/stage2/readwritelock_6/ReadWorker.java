package com.zpy.concurrency.stage2.readwritelock_6;

/**
 * 读线程
 */
public class ReadWorker extends Thread {
    private SharedData data;

    public ReadWorker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] read = data.read();
                System.out.println(Thread.currentThread().getName() + " reads:" + String.valueOf(read));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

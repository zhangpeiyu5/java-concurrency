package com.zpy.concurrency.stage1.threadgroup_7;

public class BatchThread extends Thread {
    private ThreadGroup threadGroup;
    private String threadName;

    public BatchThread(ThreadGroup threadGroup, String threadName) {
        super(threadGroup, threadName);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(Thread.currentThread().getName() + "执行中");
        }
        System.out.println(Thread.currentThread().getName() + " interrupt");
    }
}

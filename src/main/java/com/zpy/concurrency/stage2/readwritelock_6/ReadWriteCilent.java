package com.zpy.concurrency.stage2.readwritelock_6;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCilent {
    public static void main(String[] args) {
        SharedData data=new SharedData(10);
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new WriterWorker(data,"ajsslldldl").start();
        new WriterWorker(data,"12356777").start();
    }
}

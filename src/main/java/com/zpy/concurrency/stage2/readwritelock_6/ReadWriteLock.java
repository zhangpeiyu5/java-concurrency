package com.zpy.concurrency.stage2.readwritelock_6;

/**
 * 读写锁分离设计模式：
 *  读读可以并行；
 *  读写，写写不能并行。
 */
public class ReadWriteLock {
    private int readingReaders=0;
    private int waitingReaders=0;
    private int writingWriters=0;
    private int waitingWriters=0;

    public synchronized void readLock() throws InterruptedException {
        waitingReaders++;
        try{
            while (writingWriters>0){         //如果有写请求在进行，等待。
                this.wait();
            }
            readingReaders++;
        }finally {
            waitingReaders--;
        }
    }

    public synchronized void readUnlock(){
        readingReaders--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++; //进入阻塞状态
        try{
            if(readingReaders>0 || writingWriters>0){         //如果已经有读请求或者写请求在进行，等待
                this.wait();
            }
            writingWriters++;
        }finally {
            waitingWriters--;
        }
    }

    public synchronized void writeUnLock(){
        writingWriters--;
        this.notifyAll();
    }
}

package com.zpy.concurrency.stage2.singleTheadMode_5;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Client {
    public static void main(String[] args) {
        Gate gate=new Gate();
        User user1=new User("baobao","beijing",gate);
        User user2=new User("shangshang","shanghai",gate);
        User user3=new User("tiantian","tianjin",gate);
        user1.start();
        user2.start();
        user3.start();

        ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        readLock.lock();
    }
}

package com.zpy.concurrency.stage2.singleton_1;

/**
 * 线程安全的懒加载：读操作也变成了串行，性能不好。（有问题）
 */
public class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {
    }

    public synchronized static Singleton3 getInstance() {   //读操作也变成了串行，性能不好
        if (null == instance) {
            instance = new Singleton3();
        }
        return instance;
    }
}

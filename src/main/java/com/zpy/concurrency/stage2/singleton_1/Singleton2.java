package com.zpy.concurrency.stage2.singleton_1;

/**
 * 线程不安全的懒加载 （有问题）
 */
public class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (null == instance) {   //线程不安全，可能不是单例，会生成多例
            instance = new Singleton2();
        }
        return instance;
    }
}

package com.zpy.concurrency.stage2.singleton_1;

/**
 * 静态内部类方式：能够实现懒加载，单例，线程安全。（推荐）
 */
public class Singleton6 {

    private Singleton6() {

    }

    private static class InstanceHolder {   //能够实现懒加载，单例，线程安全
        private static final Singleton6 instance = new Singleton6();
    }

    public static Singleton6 getInstance() {
        return InstanceHolder.instance;
    }
}

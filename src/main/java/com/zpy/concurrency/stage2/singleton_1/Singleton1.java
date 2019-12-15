package com.zpy.concurrency.stage2.singleton_1;

/**
 * 单例模式：饿汉式，不存在线程安全问题。缺点：不能实现懒加载。
 */
public class Singleton1 {
    private static final Singleton1 instance = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }
}

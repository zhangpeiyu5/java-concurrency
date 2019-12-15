package com.zpy.concurrency.stage2.singleton_1;

/**
 * 枚举方式：懒加载、单例、线程安全。（推荐）
 */
public class Singleton7 {

    private Singleton7() {

    }

    private enum Singleton {
        INSTANCE;

        private final Singleton7 instance;

        //枚举的构造函数只执行一次，单例的
        Singleton() {
            instance = new Singleton7();
        }

        public Singleton7 getInstance() {
            return instance;
        }
    }

    public static Singleton7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}

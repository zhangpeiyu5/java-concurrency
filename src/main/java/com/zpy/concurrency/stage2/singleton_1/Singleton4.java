package com.zpy.concurrency.stage2.singleton_1;

/**
 * double check：解决了懒加载、单例问题。（有问题）
 */
public class Singleton4 {
    private static Singleton4 instance;

    private Object obj;

    private Singleton4() {
        //obj...
    }

    public static Singleton4 getInstance() {
        if (null == instance) {
            synchronized (Singleton4.class) {
                if (null == instance) {    //double check方式，解决了懒加载、单例问题。但是第2个线程去用instance的时候，Singleton4中obj可能还没准备好。
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}

package com.zpy.concurrency.stage2.singleton_1;

/**
 * double check+volatile：解决了懒加载、单例问题。（采用）
 */
public class Singleton5 {
    private static volatile Singleton5 instance;  //增加volatile。读之前所有的写操作必须完成。

    private Object obj;

    private Singleton5() {
        //obj...
    }

    public static Singleton5 getInstance() {
        if (null == instance) {
            synchronized (Singleton5.class) {
                if (null == instance) {    //double check方式，解决了懒加载、单例问题。
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}

package com.zpy.concurrency.stage1.threadgroup_7;

/**
 * 1.什么实现线程组：
 * 线程组中包含一组线程，也可以包括其他线程组，线程组形成一个树。
 */
public class ThreadGroupCreate {
    public static void main(String[] args) {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                System.out.println(getThreadGroup().getName());
                System.out.println(getThreadGroup().getParent().getName());
                System.out.println(tg1.activeCount());
            }
        };
        t1.start();
        try {
            Thread.sleep(2000);
            System.out.println("---------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tg1.activeCount());

        tg1.setDaemon(true);
        System.out.println(tg1.isDestroyed());
        tg1.destroy();
        System.out.println(tg1.isDestroyed());


//        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
//        Thread t2 = new Thread(tg2, "t2") {
//            @Override
//            public void run() {
//                System.out.println(getThreadGroup().getName());
//                System.out.println(getThreadGroup().getParent().getName());
//            }
//        };
//        t2.start();
    }


}

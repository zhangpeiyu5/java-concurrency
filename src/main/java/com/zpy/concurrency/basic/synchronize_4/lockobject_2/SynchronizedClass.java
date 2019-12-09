package com.zpy.concurrency.basic.synchronize_4.lockobject_2;

/**
 * 静态同步方法锁的对象是class：demo1对象和demo2对象不能同时调用静态同步方法test1()和静态同步方法test2(),可以同时访问静态同步方法test1()和非静态同步方法test3()。
 */
public class SynchronizedClass {

    static {
        synchronized (SynchronizedClass.class) {     //锁的是class
            System.out.println("static:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TicketDemo3 demo1 = new TicketDemo3();
        TicketDemo3 demo2 = new TicketDemo3();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo1.test1();
//                TicketDemo3.test1();
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo2.test2();
//                TicketDemo3.test2();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo2.test3();
//                TicketDemo3.test2();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

}

class TicketDemo3 {

    public static synchronized void test1() {               //静态同步方法锁的是class
        System.out.println(Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void test2() {
        System.out.println(Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void test3() {
        System.out.println(Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

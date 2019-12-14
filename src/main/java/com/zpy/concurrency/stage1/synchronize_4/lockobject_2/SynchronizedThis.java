package com.zpy.concurrency.stage1.synchronize_4.lockobject_2;

/**
 * 非静态同步方法锁的对象是this对象
 * t1和t2可以几乎同时访问sychoronized方法test1和非sychoronized方法test2,不能同时访问sychoronized方法test1和sychoronized方法test3。
 */
public class SynchronizedThis {
    public static void main(String[] args) {
        TicketDemo ticketDemo = new TicketDemo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ticketDemo.test1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ticketDemo.test2();
            }
        });

        t1.start();
        t2.start();
    }
}

class TicketDemo {
    public synchronized void test1() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void test2() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void test3() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

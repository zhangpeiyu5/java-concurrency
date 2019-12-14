package com.zpy.concurrency.stage1.synchronize_4.lockobject_2;

/**
 * 同步代码块锁的是synchronized括号内的对象
 */
public class SynchronizedObject {
    public static void main(String[] args) {
        TicketDemo2 ticketDemo = new TicketDemo2();

//        TicketDemo2 ticketDemo2 = new TicketDemo2();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ticketDemo.test1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ticketDemo.test3();
//                ticketDemo2.test3();
            }
        });

        t1.start();
        t2.start();
    }

}

class TicketDemo2 {
    private final Object object = new Object();

    public void test1() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test2() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test3() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

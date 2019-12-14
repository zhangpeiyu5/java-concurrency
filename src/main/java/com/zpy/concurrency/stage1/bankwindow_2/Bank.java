package com.zpy.concurrency.stage1.bankwindow_2;

/**
 * 多线程模拟银行3个柜台叫号的过程
 */
public class Bank {

    public static void main(String[] args) {
        //继承Thread的方式
        TicketWindowThread thread1 = new TicketWindowThread("一号柜台");
        TicketWindowThread thread2 = new TicketWindowThread("二号柜台");
        TicketWindowThread thread3 = new TicketWindowThread("三号柜台");
        thread1.start();
        thread2.start();
        thread3.start();


        //实现Runnable接口的方式
//        TicketWindowRunnable runnable = new TicketWindowRunnable();
//        Thread t1 = new Thread(runnable, "一号柜台");
//        Thread t2 = new Thread(runnable, "二号柜台");
//        Thread t3 = new Thread(runnable, "三号柜台");
//        t1.start();
//        t2.start();
//        t3.start();

    }
}


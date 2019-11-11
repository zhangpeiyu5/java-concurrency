package com.zpy.concurrency.basic.synchronize.ticket_1;


public class SynchronizedTest {

    public static void main(String[] args) {
//        TicketWindowRunnable1 ticketWindowRunnable=new TicketWindowRunnable1();

        TicketWindowRunnable2 ticketWindowRunnable = new TicketWindowRunnable2();
        Thread t1 = new Thread(ticketWindowRunnable, "t1");
        Thread t2 = new Thread(ticketWindowRunnable, "t2");
        Thread t3 = new Thread(ticketWindowRunnable, "t3");

        t1.start();
        t2.start();
        t3.start();

    }
}

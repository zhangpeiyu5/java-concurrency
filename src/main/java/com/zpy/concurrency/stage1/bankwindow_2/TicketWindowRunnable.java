package com.zpy.concurrency.stage1.bankwindow_2;

public class TicketWindowRunnable implements Runnable {

    private int index = 1;
    private final Object object = new Object();


    public void run() {
        while (index <= 500) {
            System.out.println(Thread.currentThread() + "的号码是：" + index++);   //存在线程安全问题

            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}


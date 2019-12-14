package com.zpy.concurrency.stage1.synchronize_4.ticket_1;

/**
 * 同步方法 （锁的是this对象）
 */
public class TicketWindowRunnable2 implements Runnable {

    private int index = 1;
    private final Object object = new Object();

    public void run() {
        while (ticket()) {
        }
    }


    public synchronized boolean ticket() {
        if (index > 500) {
            return false;
        } else {
            System.out.println(Thread.currentThread() + "的号码是：" + index++);   //存在线程安全问题

            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return true;
    }
}


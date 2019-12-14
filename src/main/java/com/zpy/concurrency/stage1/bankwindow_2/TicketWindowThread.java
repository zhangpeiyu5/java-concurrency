package com.zpy.concurrency.stage1.bankwindow_2;

public class TicketWindowThread extends Thread {

    private static int index = 1;      //static数据共享

    private String name;

    public TicketWindowThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (index <= 50) {
            System.out.println(Thread.currentThread() + "的号码是：" + index++);   //存在线程安全问题

            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

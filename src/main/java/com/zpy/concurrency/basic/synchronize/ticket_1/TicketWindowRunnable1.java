package com.zpy.concurrency.basic.synchronize.ticket_1;

/**
 * 同步代码块 （锁的是synchronized括号中的对象）
 */
public class TicketWindowRunnable1 implements Runnable {

    private int index = 1;
    private final Object object = new Object();


    public void run() {
        while (true) {
            synchronized (object) {         //字节码文件:monitorEnter/monitorExit
                if (index > 500) {
                    break;
                } else {
                    System.out.println(Thread.currentThread() + "的号码是：" + index++);   //存在线程安全问题

                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }
}


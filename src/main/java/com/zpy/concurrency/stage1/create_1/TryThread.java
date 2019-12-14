package com.zpy.concurrency.stage1.create_1;

public class TryThread {
    public static void main(String[] args) {
        new Thread("Thread1") {
            @Override
            public void run() {
                try {
                    System.out.println("read begin...");
                    Thread.sleep(1000 * 20L);
                    System.out.println("read end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread("Thread2") {
            @Override
            public void run() {
                try {
                    System.out.println("write begin...");
                    Thread.sleep(1000 * 20L);
                    System.out.println("write end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

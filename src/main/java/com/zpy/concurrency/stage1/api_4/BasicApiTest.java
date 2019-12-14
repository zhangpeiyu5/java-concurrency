package com.zpy.concurrency.stage1.api_4;

public class BasicApiTest {
    public static void main(String[] args) {
//        Thread t = new Thread(new Runnable() {
//            public void run() {
//                System.out.println("BasicApiTest");
//                try {
//                    Thread.sleep(10_1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        t.start();
//
//        System.out.println(t.getId());
//        System.out.println(t.getState());
//        System.out.println(t.getPriority());


        //设置优先级
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ":index=" + i);
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ":index=" + i);
                }
            }
        });


        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ":index=" + i);
                }
            }
        });

        t1.setPriority(1);
        t2.setPriority(8);
        t3.setPriority(10);
        t1.start();
        t2.start();
        t3.start();



    }
}


package com.zpy.concurrency.stage2.volatile_3;

/**
 * 一旦一个共享变量被volatile修饰，具备两层语义：
 * 1.保证了不同线程间的可见性；
 * 2.禁止对其进行重排序，也就是保证了有序性；
 * 3.并未保证原子性。
 */
public class VolatileTest {
    private static final int MAX_VALUE = 200;
    private static volatile int INIT_VALUE = 0;

    public static void main(String[] args) {
        //volatile不能保证原子性
//        new Thread(() -> {
//            while (INIT_VALUE < MAX_VALUE) {
//                System.out.println("t1->:" + (++INIT_VALUE));
//                try {
//                    Thread.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "t1").start();
//
//        new Thread(() -> {
//            while (INIT_VALUE < MAX_VALUE) {
//                System.out.println("t2->:" + (++INIT_VALUE));
//                try {
//                    Thread.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "t2").start();

        //volatile保证了不同线程间的可见性
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_VALUE) {
                INIT_VALUE = ++localValue;
                System.out.println("t3 update INIT_VALUE->:" + INIT_VALUE);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            System.out.println("t4 localValue = INIT_VALUE");
            while (INIT_VALUE < MAX_VALUE) {
                if (localValue != INIT_VALUE) {
                    localValue = INIT_VALUE;
                    System.out.println("t4 update localValue->:" + localValue);
                }
            }
        }, "t4").start();
    }
}

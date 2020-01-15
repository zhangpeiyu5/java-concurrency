package com.zpy.concurrency.stage3.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Unsafe 是 CAS 的核心类，Java 无法直接访问底层操作系统，而是通过本地 native 方法来访问。不过尽管如此，JVM还是开了一个后门：Unsafe ，它提供了硬件级别的原子操作。
 * valueOffset 为变量值在内存中的偏移地址，Unsafe 就是通过偏移地址来得到数据的原值的。
 * <p>
 * <p>
 * AtomicInteger存在ABA问题，可以通过添加版本号的方式解决，java提供了AtomicStampedReference类解决。该类的compareAndSet方法4个参数分别为：期望引用值，新引用值，期望版本值，新版本号。
 */
public class ABA {
    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 1);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            atomicInteger.compareAndSet(100, 110);
            atomicInteger.compareAndSet(110, 100);
        });


        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(20);   //让线程t1先执行，构成ABA问题，AtomicInteger能够成功设置为120。
                System.out.println("AtomicInteger:" + atomicInteger.compareAndSet(100, 120));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        t1.start();
        t2.start();
        t1.join();
        t2.join();


        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(20);    //先让t4获取到版本戳
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 110, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            atomicStampedReference.compareAndSet(110, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        });


        Thread t4 = new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("before:" + stamp);
            try {
                Thread.sleep(30);    //t4获取到版本戳之后，sleep让t3先执行，由于改变了版本戳，AtomicStampedReference设置120失败。
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after:" + atomicStampedReference.getStamp());
            System.out.println("AtomicStampedReference:" + atomicStampedReference.compareAndSet(100, 120, stamp, stamp + 1));
        });

        t3.start();
        t4.start();

    }
}

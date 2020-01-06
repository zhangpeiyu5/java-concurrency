package com.zpy.concurrency.stage3.sync_exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger:用于线程间数据的交换（只能两两交换）
 * <p>
 * 它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。
 * 这两个线程通过exchange方法交换数据，如果第一个线程先执行exchange()方法，它会一直等待第二个线程也执行exchange方法，当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。
 */
public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义一个交换对象，用来交换数据
        Exchanger<String> exchanger = new Exchanger<>();

        executorService.execute(() -> {
            String data1 = "苹果";
            System.out.println("线程" + Thread.currentThread().getName() + " 正在把" + data1 + "拿出来");
            try {
                String data2 = exchanger.exchange(data1);
                System.out.println("线程" + Thread.currentThread().getName() + " 用" + data1 + "换来了" + data2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            String data1 = "香蕉";
            System.out.println("线程" + Thread.currentThread().getName() + " 正在把" + data1 + "拿出来");
            try {
                String data2 = exchanger.exchange(data1);
                System.out.println("线程" + Thread.currentThread().getName() + " 用" + data1 + "换来了" + data2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            String data1 = "2块钱";
            System.out.println("线程" + Thread.currentThread().getName() + " 正在把" + data1 + "拿出来");
            try {
                String data2 = exchanger.exchange(data1);
                System.out.println("线程" + Thread.currentThread().getName() + " 用" + data1 + "换来了" + data2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}

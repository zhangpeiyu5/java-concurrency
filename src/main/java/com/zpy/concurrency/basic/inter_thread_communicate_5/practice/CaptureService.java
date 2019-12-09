package com.zpy.concurrency.basic.inter_thread_communicate_5.practice;

import java.util.*;

/**
 * 并不是线程数越多，执行效率越高，当线程数增加到一定数量时，再增大线程数，由于线程上下文切换，效率会变低。
 *
 * 本例中：有6个任务，每次最多只能运行3个任务，怎么保证一个任务执行完成时，立即插入另外一个任务执行。
 */
public class CaptureService {
    private static final LinkedList<Control> CONTROLS = new LinkedList<>();

    private static final int MAX_THREAD = 2;

    public static void main(String[] args) {
        List<Thread> worker = new ArrayList<>();
        //创建线程
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6").stream()
                .map(CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });

        //所有线程join
        worker.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("All of thread finished").ifPresent(System.out::println);
    }

    private static Thread createCaptureThread(String name) {
        return new Thread(() -> {
            Optional.of("the worker:" + Thread.currentThread().getName() + " begin capture data").ifPresent(System.out::println);

            //添加任务，当任务数量大于MAX_THREAD时，wait();
            synchronized (CONTROLS) {
                if (CONTROLS.size() > MAX_THREAD) {
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Control());
            }

            //执行任务
            Optional.of("the worker:" + Thread.currentThread().getName() + " is working").ifPresent(System.out::println);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //当任务执行完时，notifyAll(),从任务列表中移除。
            synchronized (CONTROLS) {
                Optional.of("the worker:" + Thread.currentThread().getName() + " end capture data").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }

        }, name);
    }

    private static class Control {

    }


}

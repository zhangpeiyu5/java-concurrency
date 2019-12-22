package com.zpy.concurrency.stage2.observer_4;

import java.util.Arrays;

/**
 * 观察者模式。获取线程生命周期状态。
 */
public class ThreadLifeCycleClient {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1", "2", "3"));
    }
}

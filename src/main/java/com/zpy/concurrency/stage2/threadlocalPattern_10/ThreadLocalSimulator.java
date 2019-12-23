package com.zpy.concurrency.stage2.threadlocalPattern_10;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟实现ThreadLocal
 * 核心思想：始终以当前线程作为map的key。
 *
 * @param <T>
 */
public class ThreadLocalSimulator<T> {
    private final Map<Thread, T> map = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            map.put(key, t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T t = map.get(key);
            if (t == null) {
                return null;
            }
            return t;
        }
    }
}

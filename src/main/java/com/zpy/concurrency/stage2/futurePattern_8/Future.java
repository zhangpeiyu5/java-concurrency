package com.zpy.concurrency.stage2.futurePattern_8;

public interface Future<T> {
    T get() throws InterruptedException;
}

package com.zpy.concurrency.stage2.futurePattern_8;

import java.util.function.Consumer;

public class FutureService {

    public <T> Future<T> submit(final FurureTask<T> task){
        AsynFuture<T> asynFuture=new AsynFuture<>();
        new Thread(()->{
            T result = task.call();
            asynFuture.done(result);
        }).start();
        return asynFuture;
    }

    //Consumer consumer回调返回结果，不用通过get()方式获取结果
    public <T> Future<T> submit(final FurureTask<T> task, Consumer consumer){
        AsynFuture<T> asynFuture=new AsynFuture<>();
        new Thread(()->{
            T result = task.call();
            asynFuture.done(result);
            consumer.accept(result);  //回调
        }).start();
        return asynFuture;
    }
}

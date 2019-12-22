package com.zpy.concurrency.stage2.observer_4;

import java.util.List;

/**
 * observer to monitor the Thread lifecycle
 */
public class ThreadLifeCycleObserver implements LifeCycleListener {
    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.stream().forEach(id -> {
            new Thread(new Observable(this) {
                @Override
                public void run() {
                    try {
                        notifyEvent(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                        System.out.println("query for the id" + id);
                        Thread.sleep(10);
                        int a=1/0;
                        notifyEvent(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                    } catch (InterruptedException e) {
                        notifyEvent(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                        e.printStackTrace();
                    }
                }
            }, id).start();
        });
    }

    @Override
    public void onEvent(Observable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("The Runnale[" + event.getThread().getName() + "] state is:" + event.getState());
            if (event.getCause() != null) {
                System.out.println("The Runnale[" + event.getThread().getName() + "] process failed");
                event.getCause().printStackTrace();
            }
        }
    }
}

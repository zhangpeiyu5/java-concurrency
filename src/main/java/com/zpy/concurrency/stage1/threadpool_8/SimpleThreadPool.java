package com.zpy.concurrency.stage1.threadpool_8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 线程池几个重要概念：
 * 1）任务队列
 * 2）拒绝策略（抛出异常、直接丢弃、阻塞、临时队列）
 * 3）init(min) size、active size、max size（min<=active<=max）
 */
public class SimpleThreadPool extends Thread {
    private static final int DEFAULT_TASK_QUEUE_SIZE = 100;   //默认任务队列大小

    public static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {   //默认线程池拒绝策略
        throw new DiscardException("discard exception");
    };

    private int size;   //工作线程数量

    private int min;    //线程池最小工作线程

    private int active;   //线程池活跃工作线程

    private int max;   //线程池最大工作线程

    private int queueSize;   //任务队列大小

    private DiscardPolicy discardPolicy;   //线程池拒绝策略

    private volatile int index = 0;

    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";    //线程名称前缀

    private static final ThreadGroup GROUP = new ThreadGroup("Pool_Group");    //线程组

    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();  //任务队列

    private static final List<WorkerTask> THREAD_QUEUE = new ArrayList<>();  //工作线程队列

    private volatile boolean destroy = false;    //线程池是否被销毁

    public SimpleThreadPool() {
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    public boolean isDestroy() {
        return destroy;
    }

    /**
     * 初始化线程池
     */
    public void init() {
        for (int i = 0; i < min; i++) {
            createWorkerTask();
        }
        this.size = min;
        this.start();  //启动线程池本身
    }

    @Override
    public void run() {
        while (!isDestroy()) {
            System.out.printf("thread pool: min:%d,active:%d,max:%d,current:%d,queueSize:%d\n", this.min, this.active, this.max, this.size, TASK_QUEUE.size());
            try {
                Thread.sleep(500);
                //线程池扩容到active
                if (TASK_QUEUE.size() > this.active && this.size < this.active) {
                    for (int i = 0; i < this.active - this.size; i++) {
                        createWorkerTask();
                    }
                    this.size = this.active;
                    System.out.println("the thread pool increment to active");
                } else if (TASK_QUEUE.size() > this.max && this.size < this.max) {
                    //线程池扩容到max
                    for (int i = 0; i < this.max - this.size; i++) {
                        createWorkerTask();
                    }
                    this.size = this.max;
                    System.out.println("the thread pool increment to max");
                }

                //没有任务时，自动回收线程
                if (TASK_QUEUE.isEmpty() && this.size > this.min) {
                    synchronized (THREAD_QUEUE) {
                        int releaseSize = this.size - this.min;
                        for (WorkerTask task : THREAD_QUEUE) {
                            if (releaseSize <= 0) {
                                break;
                            }
                            task.close();
                            task.interrupt();
                            releaseSize--;
                        }
                        this.size = this.min;
                        System.out.println("the thread pool decrement to min");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 提交任务
     *
     * @param runnable
     */
    public void submit(Runnable runnable) {
        if (destroy) {
            throw new IllegalStateException("The thread pool is already destroyed");
        }
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() >= queueSize) {
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    /**
     * 创建工作线程
     */
    public void createWorkerTask() {
        WorkerTask workerTask = new WorkerTask(GROUP, THREAD_PREFIX + (index++));
        workerTask.start();
        THREAD_QUEUE.add(workerTask);
    }

    /**
     * 关闭线程池
     *
     * @throws InterruptedException
     */
    public void shutdown() throws InterruptedException {
        System.out.println("start destroy the thread pool...");
        //等待任务执行为空
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(20);
        }

        //任务队列为空后，等待所有任务执行完毕，每个线程跳出while循环结束。
        for (WorkerTask task : THREAD_QUEUE) {
            if (task.getTaskState() == TaskState.BLOCKED) {
                task.interrupt();
                task.close();
            } else {
                Thread.sleep(20);
            }
        }
        destroy = true;
        System.out.println("end destroy the thread pool...");
    }

    /**
     * 线程状态
     */
    public enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    /**
     * 拒绝策略接口
     */
    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    /**
     * 工作线程类
     */
    public static class WorkerTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup threadGroup, String name) {
            super(threadGroup, name);
        }

        public TaskState getTaskState() {
            return this.taskState;
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("reduce");
                            break OUTER;
                        }
                    }

                    runnable = TASK_QUEUE.removeFirst();
                }

                //运行任务放在synchronized外面
                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }


            }

        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }
    }
}

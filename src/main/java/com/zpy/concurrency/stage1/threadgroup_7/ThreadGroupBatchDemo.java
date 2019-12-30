package com.zpy.concurrency.stage1.threadgroup_7;

/**
 * 在执行批量任务BatchThread的时候，如果任务不能停止，通过ThreadGroup的interrupt()方法来结束执行。
 */
public class ThreadGroupBatchDemo {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("线程组1");
        for (int i = 0; i < 5; i++) {
            BatchThread thread = new BatchThread(threadGroup, "t" + i);
            thread.start();
        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadGroup.interrupt();
        System.out.println("调用了ThreadGroup的interrupt()方法");
    }
}

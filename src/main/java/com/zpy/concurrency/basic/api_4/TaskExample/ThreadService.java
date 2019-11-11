package com.zpy.concurrency.basic.api_4.TaskExample;

public class ThreadService {
    private Thread executeThread;   //执行线程

    private boolean finished = false;

    public void execute(Thread task) {
        executeThread = new Thread() {
            @Override
            public void run() {
                task.setDaemon(true);   //守护线程
                task.start();
                try {
                    task.join();
                    finished = true;
                } catch (InterruptedException e) {
                    finished = false;
                    e.printStackTrace();
                }
            }
        };

        executeThread.start();
    }


    public void shutDown(long waitMills) {
        long currentTime = System.currentTimeMillis();

        while (!finished) {
            if (System.currentTimeMillis() - currentTime >= waitMills) {
                System.out.println("任务超时，需要结束.....");
                executeThread.interrupt();
                break;
            }

            try {
                executeThread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }

        finished = false;

    }


}

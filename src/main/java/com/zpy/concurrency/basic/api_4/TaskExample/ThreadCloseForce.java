package com.zpy.concurrency.basic.api_4.TaskExample;

/**
 * 执行一个任务时，超时强制停止执行，任务执行中可能无法走到判断标志位的地方。这时通过把任务设置为守护线程，中断主线程来结束任务。
 * todo  为什么我的没有中断，对比p17完善。
 */
public class ThreadCloseForce {
    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();

        long start = System.currentTimeMillis();
        threadService.execute(new Thread(() -> {
            while (true) {
                //process
            }
        }));

        threadService.shutDown(10_1000);
        long end = System.currentTimeMillis();

        System.out.println("任务执行了：" + (end - start) + "ms");

    }
}

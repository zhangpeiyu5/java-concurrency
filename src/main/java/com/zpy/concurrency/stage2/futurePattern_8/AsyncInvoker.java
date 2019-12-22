package com.zpy.concurrency.stage2.futurePattern_8;

/**
 * future设计模式中的几个角色：
 * 1)Future: 代表的是未来的一个凭据；
 * 2）FutureTask: 将你的调用逻辑进行隔离；
 * 3）FutureService: 桥接Future和FutureTask。
 */
public class AsyncInvoker {
    public static void main(String[] args) throws InterruptedException {
        FutureService futureService=new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finish";
        },System.out::println);

        System.out.println("-------------------");
        System.out.println("do other things");
        Thread.sleep(100);
        System.out.println("-------------------");

        System.out.println("获取结果："+future.get());


    }
}

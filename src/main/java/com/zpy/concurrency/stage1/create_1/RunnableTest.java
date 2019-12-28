package com.zpy.concurrency.stage1.create_1;

/**
 * 方法二：实现Runnable接口，重写run()方法。
 */
public class RunnableTest implements Runnable {

    @Override
    public void run() {
        System.out.println("通过实现Runnable接口方式创建线程");
    }

    public static void main(String[] args) {
        System.out.println("实现Runnable");
        Runnable runnable = new RunnableTest();
        System.out.println("创建线程");
        Thread thread = new Thread(runnable);
        System.out.println("开启线程");
        thread.start();
    }
}

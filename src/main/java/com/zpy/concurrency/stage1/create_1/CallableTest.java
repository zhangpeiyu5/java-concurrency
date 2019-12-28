package com.zpy.concurrency.stage1.create_1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 方法三：实现Callable接口的call方法
 */
public class CallableTest {

    public static void main(String[] args) {
        FutureTask task = new FutureTask(() -> {
            int count = 0;
            for (int i = 0; i < 100; i++) {
                count+=i;
            }
            return count;
        });
        //创建线程
        Thread thread = new Thread(task);
        //开启线程
        thread.start();

        try {
            System.out.println("计算结果：" + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

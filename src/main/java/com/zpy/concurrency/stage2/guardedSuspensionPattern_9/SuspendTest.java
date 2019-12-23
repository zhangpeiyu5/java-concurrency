package com.zpy.concurrency.stage2.guardedSuspensionPattern_9;

/**
 * Guarded suspension design pattern
 * 当server正在忙时，client发的请求先放在请求队列中，等server忙完之后处理请求队列中的请求。
 */
public class SuspendTest {
    public static void main(String[] args) {
        RequestQueue queue = new RequestQueue();
        new ClientThread(queue).start();

        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serverThread.close();
    }
}

package com.zpy.concurrency.stage2.guardedSuspensionPattern_9;

public class ClientThread extends Thread {
    private RequestQueue queue;

    public ClientThread(RequestQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            String msg = "hello" + i;
            System.out.println("client put request->" + msg);
            queue.putRequest(new Request(msg));
        }
    }
}

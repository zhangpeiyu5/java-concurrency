package com.zpy.concurrency.stage2.guardedSuspensionPattern_9;

public class ServerThread extends Thread {

    private RequestQueue queue;
    private volatile boolean flag = true;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (flag) {
            Request request = queue.getRequest();
            if (request == null) {
                System.out.println("server receive null");
                continue;
            }else{
                System.out.println("server get request->" + request.getValue());
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void close() {
        this.flag = false;
        this.interrupt();
    }
}

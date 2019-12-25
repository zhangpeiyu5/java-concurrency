package com.zpy.concurrency.stage2.guardedSuspensionPattern_9;

import java.util.LinkedList;
import java.util.List;

/**
 * 请求队列
 */
public class RequestQueue {
    private LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}

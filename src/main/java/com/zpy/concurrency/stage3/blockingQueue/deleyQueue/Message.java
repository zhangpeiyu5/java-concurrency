package com.zpy.concurrency.stage3.blockingQueue.deleyQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed {
    //延迟时间
    private long time;

    private String name;

    public Message(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    /**
     * 获取剩余延迟等待时间
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Message item = (Message) o;
        long diff = this.time - item.time;
        if (diff >= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Message{" +
                "time=" + time +
                ", name='" + name + '\'' +
                '}';
    }
}

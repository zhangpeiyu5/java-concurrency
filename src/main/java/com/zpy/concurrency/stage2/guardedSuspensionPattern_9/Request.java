package com.zpy.concurrency.stage2.guardedSuspensionPattern_9;

public class Request {
    private String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

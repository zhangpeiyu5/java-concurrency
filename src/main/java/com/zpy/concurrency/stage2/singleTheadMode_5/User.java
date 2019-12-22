package com.zpy.concurrency.stage2.singleTheadMode_5;

public class User extends Thread{
    private String myName;
    private String myAddress;
    private Gate gate;

    public User(String myName,String myAddress,Gate gate){
        this.myName=myName;
        this.myAddress=myAddress;
        this.gate=gate;
    }

    @Override
    public void run() {
        System.out.println(myName+" BEGIN");
        while (true){
            this.gate.pass(this.myName,this.myAddress);
        }
    }
}

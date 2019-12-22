package com.zpy.concurrency.stage2.singleTheadMode_5;

//共享资源
public class Gate {
    private int count;
    private String name;
    private String address;

    //临界值
    public synchronized void pass(String name,String address){   //通过加synchronized，实现单线程执行模式，解决了共享资源临界值的竞争问题。
        //竞争
        this.count++;
        this.name=name;
        this.address=address;
        verify();
    }

    public void verify(){
        if(this.name.charAt(0)!=this.address.charAt(0)){
            System.out.println("broke->"+toString());
        }
    }

    @Override
    public String toString() {
        return "Gate{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

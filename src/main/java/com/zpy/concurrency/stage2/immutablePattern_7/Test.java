package com.zpy.concurrency.stage2.immutablePattern_7;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ImmutableTest test=new ImmutableTest(10,"zs");
        List<String> list = test.getList();
        System.out.println(list);
        list.add("a");
        list.add("b");
        System.out.println(list);
    }
}

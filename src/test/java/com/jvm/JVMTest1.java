package com.jvm;

import org.junit.Test;

public class JVMTest1 {

    @Test
    public void test1(){
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 100000; j++) {
                sb.append("地方规范");
            }
        }

    }
}

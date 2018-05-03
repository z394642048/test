package com.chaoxing.parallelPipeline;

import org.junit.Test;

public class PStreamTest {

    @Test
    public void test1() {
        //开启3个线程来分别计算其中的一步，不同线程间通过队列来通信
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();
        for (int i = 0; i <= 1000; i++) {
            for (int j = 0; j <= 1000; j++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.orgStr = "((" + i + "+" + j + ")*" + i + ")/2";
                Plus.bq.add(msg);
            }
        }

    }
}

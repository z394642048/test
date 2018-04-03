package com.parallelism.test;

import com.sun.glass.ui.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =Application.class )
public class ThreadTest {

    @Test
    public void test1(){
        Thread thread = new Thread(){

            @Override
            public void run() {
                System.out.println("--------------");
            }
        };
        thread.start();
    }
}

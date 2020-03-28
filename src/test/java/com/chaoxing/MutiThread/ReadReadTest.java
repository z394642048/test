package com.chaoxing.MutiThread;

public class ReadReadTest {

    public static void main(String[] args) {
        final MyTask myTask = new MyTask();

        Thread t1 = new Thread(() -> myTask.read());
        t1.setName("t1");

        Thread t2 = new Thread(() -> myTask.write());
        t2.setName("t2");

        t2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }
}

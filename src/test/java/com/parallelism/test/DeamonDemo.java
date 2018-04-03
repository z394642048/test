package com.parallelism.test;

public class DeamonDemo {

    public static class DaemonT extends Thread{
        public void run(){
            while (true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonT daemonT = new DaemonT();
        daemonT.setDaemon(true);
        daemonT.start();
        Thread.sleep(2000);
    }



}

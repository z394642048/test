package com.parallelism.test;

public class MultiThreadLong {
    public static long t=0;
    public static class ChangeT implements Runnable{

        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true){
                MultiThreadLong.t=to;
                Thread.yield();
            }
        }
    }
}

package com.chaoxing.future;

public class FutureData implements Data {

    protected RealData realData = null;
    protected boolean isReady = false;

    //把装有真实操作结果的数据放入Future对象自己内部
    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        //唤醒线程
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        //判断真实操作数据是否已经放入Future对象自己内部
        while (!isReady) {
            try {
                //没有放入的话则进入等待状态
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }


}

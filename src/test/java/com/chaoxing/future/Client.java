package com.chaoxing.future;

public class Client {

    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        //新开一个线程，对数据的操作在里面
        new Thread(() -> {
            //对数据进行操作，并把操作结果封存在自己内部，速度较慢。
            RealData realData = new RealData(queryStr);
            //把封存有操作结果的对象放入Future对象中
            future.setRealData(realData);
        }).start();
        //立马返回一个Future对象
        return future;
    }
}

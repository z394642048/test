package com.chaoxing.future;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FutureTest {

    @Test
    public void index() {
        Client client = new Client();
        Data futureData = client.request("真实操作");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据 =" + futureData.getResult());
    }
}

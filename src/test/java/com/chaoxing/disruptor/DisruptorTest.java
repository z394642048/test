package com.chaoxing.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class DisruptorTest {

    @Test
    public void index() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        PCDataFactory factory = new PCDataFactory();
        //设置缓冲区大小为1024，显然是2的整数次幂
        int bufferSize=1024;
        //创建disruptor对象。它封装了整个disruptor库的使用，提供了一些便捷的API。
        Disruptor<PCData> disruptor = new Disruptor<>(factory,bufferSize,executor, ProducerType.MULTI,new BlockingWaitStrategy());
        //设置用于处理数据的消费者。这里设置了4个消费者实例，系统会为每一个消费者实例映射到一个线程中，也就是提供了4个消费者线程。
        disruptor.handleEventsWithWorkerPool(new Consumer(),new Consumer(),new Consumer(),new Consumer());
        //启动并初始化disruptor系统。
        disruptor.start();
        //得到环形缓冲区
        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        //把环形缓冲区放入生产者中
        Producer producer = new Producer(ringBuffer);
        //分配一个容量为8的ByteBuffer对象（字节缓冲区）
        ByteBuffer bb = ByteBuffer.allocate(8);
        //由一个生产者不断地向缓冲区
        for (long l = 0; true; l++) {
            //将数据放入ByteBuffer对象中
            bb.putLong(0,l);
            //将ByteBuffer对象推入到环形缓冲区中
            producer.pushData(bb);
            Thread.sleep(100);
            System.out.println("add data "+l);
        }
    }
}

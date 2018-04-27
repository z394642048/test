package com.chaoxing.disruptor;



import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class Producer {
    //环形缓冲区
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer bb){
        //得到下一个可用的序列号
        long sequence = ringBuffer.next();
        try {
            //通过序列号得到空闲可用的PCData
            PCData event = ringBuffer.get(sequence);
            //将bb值放入PCData中，这个值最终会传递给消费者
            event.setValue(bb.getLong(0));
        }finally {
            //将该数据发布到Disruptor的环形队列中
            ringBuffer.publish(sequence);
        }
    }
}

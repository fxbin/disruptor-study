package cn.fxbin.learn.disruptor.spike;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * SpikeEventMain
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 13:38
 */
public class SpikeEventMain {


    public static void main(String[] args) {
        producerWithTranslator();
    }

    public static void producerWithTranslator() {

        int bufferSize = 1024;

        // 创建disruptor
        Disruptor<SpikeEvent> disruptor = new Disruptor<SpikeEvent>(SpikeEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

        // 连接消费事件方法
        disruptor.handleEventsWith(new SpikeEventHandler());

        // 启动
        disruptor.start();

        RingBuffer<SpikeEvent> ringBuffer = disruptor.getRingBuffer();
        SpikeEventProducer spikeEventProducer = new SpikeEventProducer(ringBuffer);

        for(long i = 0; i< 10; i++){
            spikeEventProducer.onData(i, i);
        }
        //关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
        disruptor.shutdown();
    }

}

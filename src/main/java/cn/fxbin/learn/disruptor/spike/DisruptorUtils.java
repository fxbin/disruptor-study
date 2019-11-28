package cn.fxbin.learn.disruptor.spike;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * DisruptorUtils
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 13:53
 */
public class DisruptorUtils {

    static Disruptor<SpikeEvent> disruptor = null;

    static {
        int bufferSize = 1024;

        // 创建disruptor
        disruptor = new Disruptor<SpikeEvent>(SpikeEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

        // 连接消费事件方法
        disruptor.handleEventsWith(new SpikeEventHandler());

        // 启动
        disruptor.start();
    }

    public static void producer(SpikeEvent spikeEvent){
        RingBuffer<SpikeEvent> ringBuffer = disruptor.getRingBuffer();
        SpikeEventProducer producer = new SpikeEventProducer(ringBuffer);
        producer.onData(spikeEvent.getSpikeId(), spikeEvent.getUserId());
    }

}

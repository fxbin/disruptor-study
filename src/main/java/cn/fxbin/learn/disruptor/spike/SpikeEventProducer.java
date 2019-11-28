package cn.fxbin.learn.disruptor.spike;

import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;

/**
 * SpikeEventProducer 使用translator方式生产者
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 11:32
 */
public class SpikeEventProducer {

    private final RingBuffer<SpikeEvent> ringBuffer;

    public SpikeEventProducer(RingBuffer<SpikeEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorVararg<SpikeEvent> TRANSLATOR = new EventTranslatorVararg<SpikeEvent>() {

        @Override
        public void translateTo(SpikeEvent event, long sequence, Object... args) {
            event.setSpikeId((Long) args[0]);
            event.setUserId((Long) args[1]);
        }
    };

    public void onData(Long spikeId, Long userId) {
        ringBuffer.publishEvent(TRANSLATOR, spikeId, userId);
    }

}

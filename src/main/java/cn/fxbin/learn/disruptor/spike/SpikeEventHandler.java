package cn.fxbin.learn.disruptor.spike;

import com.lmax.disruptor.EventHandler;

import javax.annotation.Resource;

/**
 * SpikeEventHandler 事件消费者，处理事件
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 11:25
 */
public class SpikeEventHandler implements EventHandler<SpikeEvent> {

    SpikeService spikeService = (SpikeService) SpringUtils.getBean(SpikeService.class);

    @Override
    public void onEvent(SpikeEvent spikeEvent, long sequence, boolean endOfBatch) throws Exception {
        spikeService.startSpike(spikeEvent.getSpikeId(), spikeEvent.getUserId());
    }
}

package cn.fxbin.learn.disruptor.spike;

import com.lmax.disruptor.EventFactory;

/**
 * SpikeEventFactory 事件生成工厂（用来初始化预分配事件对象）
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 11:24
 */
public class SpikeEventFactory implements EventFactory<SpikeEvent> {
    @Override
    public SpikeEvent newInstance() {
        return new SpikeEvent();
    }
}

package cn.fxbin.learn.disruptor.demo.factory;

import cn.fxbin.learn.disruptor.demo.model.LongEvent;
import com.lmax.disruptor.EventFactory;

/**
 * LongEventFactory
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 10:22
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}

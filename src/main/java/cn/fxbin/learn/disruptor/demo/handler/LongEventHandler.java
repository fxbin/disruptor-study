package cn.fxbin.learn.disruptor.demo.handler;

import cn.fxbin.learn.disruptor.demo.model.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * LongEventHandler
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 10:24
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Event:" + event);
    }
}

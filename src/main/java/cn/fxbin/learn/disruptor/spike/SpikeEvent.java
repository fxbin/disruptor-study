package cn.fxbin.learn.disruptor.spike;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SpikeEvent 事件对象
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 11:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpikeEvent {

    private long spikeId;

    private long userId;

}

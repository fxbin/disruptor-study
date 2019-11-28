package cn.fxbin.learn.disruptor.spike;

import org.springframework.stereotype.Service;

/**
 * SpikeServiceImpl
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 11:53
 */
@Service
public class SpikeServiceImpl implements SpikeService {




    @Override
    public Result startSpike(long spikeId, long userId) {
        System.out.println("spikeId: " + spikeId + "; userId:"+ userId);
        // 这里就先只打印一下子叭...
        // TODO 具体业务逻辑
        return Result.success();
    }
}

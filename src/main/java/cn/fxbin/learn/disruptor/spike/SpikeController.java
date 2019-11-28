package cn.fxbin.learn.disruptor.spike;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpikeController
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 13:57
 */
@RestController
public class SpikeController {

    @GetMapping("/test")
    public Result test() {

        for (long i = 0; i <100 ; i++) {
            DisruptorUtils.producer(new SpikeEvent(i, i));
        }
        return Result.success();
    }

}

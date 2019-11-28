package cn.fxbin.learn.disruptor.spike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 11:48
 */
@Data
@Builder
@AllArgsConstructor
public class Result<T> {

    private int errcode;

    private String errmsg;

    private T data;

    private final static String SUCCESS_MAG = "Successfully";

    public Result(T data) {
        this(0, SUCCESS_MAG, data);

    }

    public Result() {
        this(0, SUCCESS_MAG, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> success() {
        return new Result<T>();
    }


}

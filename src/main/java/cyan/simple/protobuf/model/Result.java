package cyan.simple.protobuf.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>ResponseResult</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 9:24 2021/4/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    public static Integer OK = 200;
    public static Integer ERROR = 400;
    /** 状态码 */
    private Integer status;
    /** 信息 */
    private String message;
    /** 数据 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    /** 时间 */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @Builder.Default
    private Date timestamp = new Date();

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    private Result(T data) {
        this.status = OK;
        this.message = "成功";
        this.data = data;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }
}

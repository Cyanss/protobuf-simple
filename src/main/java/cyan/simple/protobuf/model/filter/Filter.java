package cyan.simple.protobuf.model.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>ClassFilter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 14:42 2021/4/2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filter implements Serializable {
    /** 名称 */
    private String name;

    //TODO 其他查询条件
}

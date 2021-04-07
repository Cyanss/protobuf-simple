package cyan.simple.protobuf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Teacher</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 14:22 2021/4/2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {
    /** 姓名 */
    private String name;
    /** 年龄 */
    private Integer age;
    /** 性别 */
    private String sex;
    /** 学科 */
    private String subject;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Teachers implements Serializable {
        /** 静态内部类 用于映射 proto文件中的TeachersMessage*/
        private List<Teacher> teachers;
    }
}

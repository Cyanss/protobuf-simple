package cyan.simple.protobuf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Student</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 14:18 2021/4/2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    /** 名称 */
    private String name;
    /** 年龄 */
    private Integer age;
    /** 性别 */
    private String sex;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Students implements Serializable {
        /** 静态内部类 用于映射 proto文件中的StudentsMessage*/
        private List<Student> students;
    }
}

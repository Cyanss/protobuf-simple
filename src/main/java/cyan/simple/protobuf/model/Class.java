package cyan.simple.protobuf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Class</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 14:36 2021/4/2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Class implements Serializable {
    /** 班级名称 3年2班 */
    private String name;
    /** 学生数量 */
    private Integer size;
    /** 教师列表 */
    private List<Teacher> teachers;
    /** 学生列表 */
    private List<Student> students;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Classes implements Serializable {
        /** 静态内部类 用于映射 proto文件中的ClassesMessage*/
        private List<Class> classes;
    }
}

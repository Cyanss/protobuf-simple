package cyan.simple.protobuf.service.impl;

import cyan.simple.protobuf.model.Teacher;
import cyan.simple.protobuf.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>TeacherServiceImpl</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 10:38 2021/4/7
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    /**
     * 异常处理、事务回滚省略
     * @param name 名称
     * @return Teacher
     */
    @Override
    public Teacher queryByTeacherName(String name) {
        /** 模拟通过教师名称查询教师数据服务 */
        if (name != null && name.trim().length() > 0) {
            return Teacher.builder().name(name).age(28).sex("男").subject("数学").build();
        }
        /** 教师名称参数为空的异常抛出省略 */
        return null;
    }

    /**
     * 异常处理、事务回滚省略
     * @return Teacher.Teachers
     */
    @Override
    public Teacher.Teachers queryAll() {
        /** 模拟班级列表查询服务 */
        Teacher.Teachers.TeachersBuilder builder = Teacher.Teachers.builder();
        Teacher teacher = Teacher.builder().name("王英语").age(31).sex("女").subject("英语").build();
        builder.teachers(Arrays.asList(teacher,queryByTeacherName("李高数")));
        return builder.build();
    }
}

package cyan.simple.protobuf.service.impl;

import cyan.simple.protobuf.model.Student;
import cyan.simple.protobuf.service.StudentService;
import cyan.simple.protobuf.util.GeneralUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>StudentServiceImpl</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 10:33 2021/4/7
 */
@Service
public class StudentServiceImpl implements StudentService {

    /**
     * 异常处理、事务回滚省略
     * @param name 名称
     * @return Student
     */
    @Override
    public Student queryByStudentName(String name) {
        /** 模拟通过学生名称查询学生数据服务 */
        if (GeneralUtils.isNotEmpty(name)) {
            return Student.builder().name(name).age(15).sex("男").build();
        }
        /** 学生名称参数为空的异常抛出省略 */
        return null;
    }

    /**
     * 异常处理、事务回滚省略
     * @return Student.Students
     */
    @Override
    public Student.Students queryAll() {
        /** 模拟班级列表查询服务 */
        Student.Students.StudentsBuilder builder = Student.Students.builder();
        Student student = Student.builder().name("张小花").age(14).sex("女").build();
        builder.students(Arrays.asList(student,queryByStudentName("王小明")));
        return builder.build();
    }
}

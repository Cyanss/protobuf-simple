package cyan.simple.protobuf.service.impl;

import cyan.simple.protobuf.model.Class;
import cyan.simple.protobuf.model.Student;
import cyan.simple.protobuf.model.Teacher;
import cyan.simple.protobuf.service.ClassService;
import cyan.simple.protobuf.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

/**
 * <p>ClassServiceImpl</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 8:44 2021/4/6
 */
@Service
public class ClassServiceImpl implements ClassService {

    /**
     * 异常处理、事务回滚省略
     * @param name 名称
     * @return Class
     */
    @Override
    public Class queryByClassName(String name) {
        /** 模拟通过班级名称查询班级数据服务 */
        if (GeneralUtils.isNotEmpty(name)) {
            Class.ClassBuilder classBuilder = Class.builder();
            classBuilder.name(name).size(45);
            Teacher teacher = Teacher.builder().name("李高数").age(28).sex("男").subject("数学").build();
            classBuilder.teachers(Collections.singletonList(teacher));
            Student student = Student.builder().name("王小明").age(15).sex("男").build();
            classBuilder.students(Collections.singletonList(student));
            return classBuilder.build();
        }
        /** 班级名称参数为空的异常抛出省略 */
        return null;
    }

    /**
     * 异常处理、事务回滚省略
     * @return Class.Classes
     */
    @Override
    public Class.Classes queryAll() {
        /** 模拟班级列表查询服务 */
        Class.Classes.ClassesBuilder builder = Class.Classes.builder();
        Class.ClassBuilder classBuilder = Class.builder();
        classBuilder.name("三年1班").size(45);
        Teacher teacher = Teacher.builder().name("王英语").age(31).sex("女").subject("英语").build();
        classBuilder.teachers(Collections.singletonList(teacher));
        Student student = Student.builder().name("张小花").age(14).sex("女").build();
        classBuilder.students(Collections.singletonList(student));
        builder.classes(Arrays.asList(classBuilder.build(),queryByClassName("三年2班")));
        return builder.build();
    }
}

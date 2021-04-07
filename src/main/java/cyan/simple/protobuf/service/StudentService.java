package cyan.simple.protobuf.service;

import cyan.simple.protobuf.model.Class;
import cyan.simple.protobuf.model.Student;

/**
 * <p>StudentService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 10:30 2021/4/7
 */
public interface StudentService {

    /**
     * 模拟通过学生名称查询学生信息
     * 注：异常抛出省略
     * @param name 名称
     * @return 班级信息
     */
    Student queryByStudentName(String name);

    /**
     * 模拟所有学生信息查询
     * 通常写法应该是 <code> List<Student> queryAll() </code>
     * 为了对应到 <code> StudentsMessage </code>类，这里使用静态内部类
     * 注：异常抛出省略
     * @return 班级信息列表
     */
    Student.Students queryAll();

    //TODO 其他服务方法
}

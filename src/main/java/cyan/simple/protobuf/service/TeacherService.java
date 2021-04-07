package cyan.simple.protobuf.service;

import cyan.simple.protobuf.model.Teacher;

/**
 * <p>TeacherService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 10:30 2021/4/7
 */
public interface TeacherService {

    /**
     * 模拟通过教师名称查询教师信息
     * 注：异常抛出省略
     * @param name 名称
     * @return 班级信息
     */
    Teacher queryByTeacherName(String name);

    /**
     * 模拟所有教师信息查询
     * 通常写法应该是 <code> List<Teacher> queryAll() </code>
     * 为了对应到 <code> TeachersMessage </code>类，这里使用静态内部类
     * 注：异常抛出省略
     * @return 班级信息列表
     */
    Teacher.Teachers queryAll();

    //TODO 其他服务方法
}

package cyan.simple.protobuf.service;

import cyan.simple.protobuf.model.Class;

/**
 * <p>ClassService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 14:41 2021/4/2
 */
public interface ClassService {
    /**
     * 模拟通过班级名称查询班级信息
     * 注：异常抛出省略
     * @param name 名称
     * @return 班级信息
     */
    Class queryByClassName(String name);

    /**
     * 模拟所有班级信息查询
     * 通常写法应该是 <code> List<Class> queryAll() </code>
     * 为了对应到 <code> ClassesMessage </code>类，这里使用静态内部类
     * 注：异常抛出省略
     * @return 班级信息列表
     */
    Class.Classes queryAll();

    //TODO 其他服务方法
}

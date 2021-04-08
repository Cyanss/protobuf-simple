
#SpringBoot + GoogleProtobuf

项目地址: [protobuf-simple](https://github.com/Cyanss/protobuf-simple) ：https://github.com/Cyanss/protobuf-simple

## 一、环境配置

   &emsp;&emsp;<code>protobuf-java</code>版本的选择需要对应到<code>C++</code>等其他端的版本以及Maven中<code>protobuf-java</code>版本。
   这里选择3.4.0版本进行演示及测试。

- 电脑系统: 
> <code> Windows 10 x64 bit </code>

- protobuf GitHub: 

> <link>https://github.com/protocolbuffers/protobuf</link>

- protobuf 源码下载:
 > [protobuf-java-3.4.0.zip](https://github.com/protocolbuffers/protobuf/releases/download/v3.4.0/protobuf-java-3.4.0.zip)
 
 > https://github.com/protocolbuffers/protobuf/releases/download/v3.4.0/protobuf-java-3.4.0.zip

- protoc 编译器下载:
 > [protoc-3.4.0-win32.zip](https://github.com/protocolbuffers/protobuf/releases/download/v3.4.0/protoc-3.4.0-win32.zip)
 
 > https://github.com/protocolbuffers/protobuf/releases/download/v3.4.0/protoc-3.4.0-win32.zip
 
 > 注：<code>protoc</code>编译器3.4.0版本只有32位安装包。

- protobuf-java 依赖: 
```xml
<dependency>
    <groupId>com.google.protobuf</groupId>
    <artifactId>protobuf-java</artifactId>
    <version>3.4.0</version>
    <scope>compile</scope>
</dependency>
```

- Json转换工具:
```xml
<dependency>  
    <groupId>com.googlecode.protobuf-java-format</groupId>  
    <artifactId>protobuf-java-format</artifactId>  
    <version>1.4</version>  
</dependency>  
```
- Protoman测试工具:

 > [Protoman github](https://github.com/haizhibin1989/Protoman) : https://github.com/haizhibin1989/Protoman

 > [Protoman_Setup_0.4.0.exe](https://github.com/spluxx/Protoman/releases/download/v0.4.0/Protoman.Setup.0.4.0.exe): https://github.com/haizhibin1989/Protoman
 
 &emsp;&emsp;将相关的工具下载，小伙伴们也可以根据自己的环境以及需求选择其他版本或者环境的。
 
- protobuf安装配置：

 &emsp;&emsp;<code>D</code>盘新建<code>Protobuf</code>文件夹，我们将下载的<code>protobuf</code>和<code>protoc</code>
 编译器分别解压到这个文件夹.
 ```text
├─Protobuf
│  ├─protobuf-3.4.0
│  │  ├─benchmarks
│  │  ├─cmake
│  │  ├─conformance
│  │  ├─editors
│  │  ├─examples
│  │  ├─gmock
│  │  ├─java
│  │  ├─m4
│  │  ├─objectivec
│  │  ├─python
│  │  ├─src
│  │  └─util
│  ├─protoc-3.4.0-win32
│  │  ├─bin
│  │  └─include
```
&emsp;&emsp;右键我的电脑&emsp;>&emsp;属性&emsp;> &emsp;高级系统设置&emsp;> &emsp;环境变量。

&emsp;&emsp;在环境变量的系统变量选项新建系统变量：

- &emsp;&emsp;<code>PROTO_SRC_HOME</code>值为：<code>D:\Protobuf\protobuf-3.4.0</code>

- &emsp;&emsp;<code>PROTO_HOME</code>值为：<code>D:\Protobuf\protoc-3.4.0-win32</code>

&emsp;&emsp;选择<code>Path</code>&emsp;>&emsp;编辑&emsp;>&emsp;新建，<code>%PROTO_HOME%\bin</code>和<code>%PROTO_SRC_HOME%\src</code>

&emsp;&emsp;按快捷键<code>Win+X</code>&emsp;>&emsp;Windows PowerShell(I)。

&emsp;&emsp;输入<code>protoc --version</code>验证protoc编译器环境配置：

```text
Windows PowerShell
版权所有 (C) 2016 Microsoft Corporation。保留所有权利。

PS C:\Users\Cyan> protoc --version
libprotoc 3.4.0
```

## 二、项目配置

&emsp;&emsp;打开IDEA新建<code>protobuf-simple</code>项目，删除暂时用不到的<code>.mvn</code>、<code>HELP.md</code>、
<code>mvnw</code>、<code>mvnw.cmd</code>和<code>resources</code>目录下的<code>static</code>、<code>templates</code>、<code>test</code>文件，
引入我们需要的Mave依赖，<code>pom.xml</code>文件如下：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>cyan.simple</groupId>
    <artifactId>protobuf-simple</artifactId>
    <version>0.1.0-SNAPSHOT</version>
    <name>protobuf-simple</name>
    <description>protobuf simple project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <repositories>
        <repository>
            <id>aliyun-central</id>
            <name>aliyun maven centrals</name>
            <url>https://maven.aliyun.com/repository/central</url>
        </repository>
        <repository>
            <id>aliyun-google</id>
            <name>aliyun maven google</name>
            <url>https://maven.aliyun.com/repository/google</url>
        </repository>
        <repository>
            <id>aliyun-spring</id>
            <name>aliyun maven spring</name>
            <url>https://maven.aliyun.com/repository/spring</url>
        </repository>
        <repository>
            <id>aliyun-spring-plugin</id>
            <name>aliyun maven spring-plugin</name>
            <url>https://maven.aliyun.com/repository/spring-plugin</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.18</version>
            <scope>compile</scope>
        </dependency>

        <dependency>
            <groupId>com.google.protobuf</groupId>
            <artifactId>protobuf-java</artifactId>
            <version>3.4.0</version>
            <scope>compile</scope>
        </dependency>

        <dependency>
            <groupId>com.googlecode.protobuf-java-format</groupId>
            <artifactId>protobuf-java-format</artifactId>
            <version>1.4</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <finalName>${project.artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>

```

&emsp;&emsp;<code>Ctrl+Alt+S</code>打开设置，找到<code>Plugins</code>安装<code>proto</code>相关插件，在<code>Marketplace</code>中搜索<code>proto</code>,选择<code>GenProtobuf</code>、
<code>Protobuf Support</code>、<code>Protobuf Highlight</code>插件。

&emsp;&emsp;安装完成重启IDEA后，<code>Ctrl+Alt+S</code>打开设置,在<code>Settings</code>中搜索<code>proto</code>,
找到<code>Tools</code>下的<code>ProtoBuf Compiler</code>配置<code>protoc</code>的路径: <code>D:\Protobuf\protoc-3.4.0-win32\bin\protoc.exe</code>。

&emsp;&emsp;关闭设置界面，选择<code>Tools</code>目录下的<code>Configure GenProtobuf</code>,选择<code>protoc</code>的路径：<code>D:\Protobuf\protoc-3.4.0-win32\bin\protoc.exe</code>。
语言配置选择<code>Java</code>,勾选下方文件生成路径中的<code>Java</code>选项，选择生成路径为当前项目下的<code>..\java</code>。

```text
protoc path: D:\Protobuf\protoc-3.4.0-win32\bin\protoc.exe

Quick Gen  : Java

            Paths to save output, absolute or relative to proto file

Python     :

C++        :

......

Java       : ..\java

......

```

> 注：如果<code>protoc</code>的环境变量配置没问题，这里的<code>protoc</code>的路径配置也可以是<code>protoc</code>:
>    ```text
>    protoc path: protoc
>    
>    Quick Gen  : Java
>
>    ......
>    ```
 
## 三、Proto文件编写
 
&emsp;&emsp;选择<code>main</code>包下新建<code>proto</code>包用来存放<code>.proto</code>文件。我们使用学生、老师以及班级的数据模型来模拟<code>protobuf</code>接口的访问。
分别新建编写如下<code>proto</code>文件：

- > 学生protobuf文件 <code>StudentProto.proto</code>
```proto

syntax = "proto3";
package proto;
option java_package = "cyan.simple.protobuf.model.proto";
option java_outer_classname = "StudentProto";

message StudentsMessage{
    repeated StudentMessage students = 1;
}

message StudentMessage{
    // 姓名
    string name = 1;
    // 年龄
    int32 age = 2;
    // 性别
    string sex = 3;
}
```
- > 教师protobuf文件 <code>TeacherProto.proto</code>
```proto
syntax = "proto3";
package proto;
option java_package = "cyan.simple.protobuf.model.proto";
option java_outer_classname = "TeacherProto";


message TeachersMessage{
    repeated TeacherMessage teachers = 1;
}

message TeacherMessage{
    // 姓名
    string name = 1;
    // 年龄
    int32 age = 2;
    // 性别
    string sex = 3;
    // 学科
    string subject = 4;
}
```
- > 班级protobuf文件 <code>ClassProto.proto</code>
```proto
syntax = "proto3";
package proto;
import "TeacherProto.proto";
import "StudentProto.proto";
option java_package = "cyan.simple.protobuf.model.proto";
option java_outer_classname = "ClassProto";

message ClassesMessage {
    repeated ClassMessage classes = 1;
}

message ClassMessage{
    // 班级名称
    string name = 1;
    // 班级人数
    int32 size = 2;
    // 教师列表
    repeated TeacherMessage teachers = 3;
    // 学生列表
    repeated StudentMessage students = 4;
}

```
- > 响应结果protobuf文件 <code>ResultProto.proto</code>
```proto
syntax = "proto3";
package proto;
import "ClassProto.proto";
import "TeacherProto.proto";
import "StudentProto.proto";
option java_package = "cyan.simple.protobuf.model.proto";
option java_outer_classname = "ResultProto";

message ResultMessage {
    // 状态码
    int32 status        = 1;
    // 信息
    string message      = 2;
    // 数据
    oneof data {
        // 单个班级信息
        ClassMessage class = 3;
        // 这里没办法直接写 repeated ClassMessage classes = 4;
        // 需要在外部包装一层对象
        ClassesMessage classes = 4;
        // 单个教师信息
        TeacherMessage teacher = 5;
        // 这里没办法直接写 repeated TeacherMessage teachers = 6;
        // 需要在外部包装一层对象
        TeachersMessage teachers = 6;
        // 单个学生信息
        StudentMessage student = 7;
        // 这里没办法直接写 repeated TeacherMessage teachers = 8;
        // 需要在外部包装一层对象
        StudentsMessage students = 8;
    }
    // 时间
    int64 timestamp     = 9;
}
```
- > 查询参数protobuf文件 <code>FilterProto.proto</code>
```proto
syntax = "proto3";
package proto;
option java_package = "cyan.simple.protobuf.model.proto";
option java_outer_classname = "FilterProto";


message FilterBodyMessage{
    oneof body {
        FilterMessage filter = 1;
    }
}

message FilterMessage{
    // 根据名称查询
    string name = 1;
}

```
> 关于proto文件编写请参考官方文档[Language Guide (proto3)](https://developers.google.cn/protocol-buffers/docs/proto3)或者网友的中文翻译[Protobuf 语言指南(proto3)](https://blog.csdn.net/sanshengshui/article/details/82950531),
这里就不再赘述。

&emsp;&emsp;接下来选中所有编写好的<code>proto</code>文件，鼠标右键使用<code>quick gen protobuf rules</code>生成<code>java</code>文件到<code>cyan.simple.protobuf.model.proto</code>包下，
<code>GenProtobuf</code>插件不报其他错误并生成文件说明生成<code>java</code>成功。

## 四、Pojo文件编写

&emsp;&emsp;在<code>cyan.simple.protobuf.model</code>包下新建<code>java</code>相关<code>Pojo</code>类,这里使用<code>lombok</code>相关注解快速编写(正式项目或团队项目不建议如此):

- > 学生Pojo类 <code>Student.java</code>
```java
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
```
- > 教师Pojo类 <code>Teacher.java</code>
```java
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
```
- > 班级Pojo类 <code>Class.java</code>
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Class implements Serializable {
    /** 班级名称 3年1班 */
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
```
- > 响应结果Pojo类 <code>Result.java</code>
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    public static Integer OK = 200;
    public static Integer ERROR = 400;
    /** 状态码 */
    private Integer status;
    /** 信息 */
    private String message;
    /** 数据 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    /** 时间 */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date timestamp = new Date();

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    private Result(T data) {
        this.status = OK;
        this.message = "成功";
        this.data = data;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }
}
```
&emsp;&emsp;在<code>cyan.simple.protobuf.model</code>包下新建<code>filter</code>包，新建<code>filter.java</code>类，由于是模拟接口访问，
这里学生<code>Student</code>、教师<code>Teacher</code>和班级<code>Class</code>的<code>POST</code>请求使用同一个<code>filter</code>类映射查询条件。

- > 查询参数Pojo类 <code>Result.java</code>
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filter implements Serializable {
    /** 名称 */
    private String name;

    //TODO 其他查询条件
}
```

## 五、Service文件编写

&emsp;&emsp;在<code>cyan.simple.protobuf</code>包下新建<code>util</code>包,将个人常用的两个工具类<code>GeneralUtils.java</code>和<code>JsonUtils.java</code>复制进来,具体代码请参考[protobuf-simple](https://github.com/Cyanss/protobuf-simple)项目工具包。

&emsp;&emsp;在<code>cyan.simple.protobuf</code>包下新建<code>service</code>包,分别新建相关<code>service</code>接口类(快速粘贴复制，Ctrl+F替换)。

- > 学生Service接口类 <code>StudentService.java</code>
```java
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
```

- > 教师Service接口类 <code>TeacherService.java</code>
```java
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
```

- > 班级Service接口类 <code>ClassService.java</code>
```java
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
```

&emsp;&emsp;在<code>cyan.simple.protobuf.service</code>包下新建<code>impl</code>包,分别新建相关<code>service</code>接口实现类(快速粘贴复制，Ctrl+F替换)。

- > 学生Service实现类 <code>StudentServiceImpl.java</code>
```java
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
```
- > 教师Service实现类 <code>TeacherServiceImpl.java</code>
```java
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
        if (GeneralUtils.isNotEmpty(name)) {
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
```
- > 班级Service实现类 <code>ClassServiceImpl.java</code>
```java
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

```
## 六、配置文件编写

&emsp;&emsp;在<code>proto</code>数据模型和<code>java</code>的<code>pojo</code>数据模型转换中我们使用了<code>protobuf-java-format</code>依赖，
当编写的<code>java</code>的<code>pojo</code>类结构和<code>proto</code>文件的结构一致的情况下，我们可以快速的实现二者的转换，而不必对二者进行深度遍历及属性拷贝。

&emsp;&emsp;在<code>cyan.simple.protobuf</code>包下新建<code>configure</code>包,新建<code>ProtobufAutoConfigure</code>配置类，初始化<code>FormatFactory</code>Bean对象。

```java
@Slf4j
@Configuration
@ComponentScan(basePackages = {"cyan.simple.protobuf"})
public class ProtobufAutoConfigure {
    public ProtobufAutoConfigure() {
        log.info("================= protobuf-simple-configure initiated ！ ===================");
    }

    @Bean
    public FormatFactory formatFactory() {
        return new FormatFactory();
    }
}
```

&emsp;&emsp;修改<code>ProtobufApplication.java</code>启动类，继承<code>SpringBootServletInitializer</code>类。

```java
@SpringBootApplication
public class ProtobufApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ProtobufApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProtobufApplication.class);
    }
}
```
&emsp;&emsp;启动项目查看日志输出。
```text

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.4.4)

2021-04-08 08:46:56.208  INFO 18428 --- [           main] c.simple.protobuf.ProtobufApplication    : Starting ProtobufApplication using Java 1.8.0_92 on BlueThink with PID 18428 .....
2021-04-08 08:46:56.212  INFO 18428 --- [           main] c.simple.protobuf.ProtobufApplication    : No active profile set, falling back to default profiles: default
2021-04-08 08:46:56.867  INFO 18428 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2021-04-08 08:46:56.874  INFO 18428 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-04-08 08:46:56.874  INFO 18428 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.44]
2021-04-08 08:46:56.925  INFO 18428 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-04-08 08:46:56.925  INFO 18428 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 670 ms
2021-04-08 08:46:56.965  INFO 18428 --- [           main] c.s.p.configure.ProtobufAutoConfigure    : ================= protobuf-simple-configure initiated ！ ===================
2021-04-08 08:46:57.092  INFO 18428 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2021-04-08 08:46:57.216  INFO 18428 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-04-08 08:46:57.223  INFO 18428 --- [           main] c.simple.protobuf.ProtobufApplication    : Started ProtobufApplication in 1.461 seconds (JVM running for 2.968)

```

## 七、Controller编写

&emsp;&emsp;在<code>cyan.simple.protobuf</code>包下新建<code>controller</code>包,分别新建相关<code>Controller</code>接口(快速粘贴复制，Ctrl+F替换)。

- > 学生Controller接口 <code>StudentController.java</code>

```java
@CrossOrigin
@RestController
@RequestMapping("/rest/v0.1.0/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * RestFul 通过名称查询学生信息查询接口
     * @param name 学生名称
     * @return ResponseEntity
     */
    @GetMapping("/get/name")
    public ResponseEntity getStudentByName(@RequestParam("name") String name) {
        Student result = studentService.queryByStudentName(name);
        return ResponseEntity.ok(Result.ok(result));
    }

    @GetMapping("/get/all")
    public ResponseEntity getStudentAll() {
        Student.Students students = studentService.queryAll();
        return ResponseEntity.ok(Result.ok(students.getStudents()));
    }

    @PostMapping("/post/name")
    public ResponseEntity postStudentByName(@RequestBody Filter filter) {
        String name = filter.getName();
        /** 参数校验 异常抛出等省略 */
        Student result = studentService.queryByStudentName(name);
        return ResponseEntity.ok(Result.ok(result));
    }


    @PostMapping("/proto/name")
    public void protoStudentByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** getOutputStream 抛出的IO异常直接抛出 */
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            Filter filter = ProtoHandler.parserFilter(request);
            String name = filter.getName();
            /** 参数校验 异常抛出等省略 */
            Student result = studentService.queryByStudentName(name);
            StudentProto.StudentMessage.Builder studentBuilder = StudentProto.StudentMessage.newBuilder();
            ProtoHandler.formatMessage(ProtoHandler.getFormatter(),result,studentBuilder);
            /** oneOf 字段设置对象 需要单独设置 */
            ResultProto.ResultMessage.Builder resultBuilder = ResultProto.ResultMessage.newBuilder();
            resultBuilder.mergeStudent(studentBuilder.build()).setStatus(Result.OK)
                    .setMessage("成功").setTimestamp(new Date().getTime());
            ResultProto.ResultMessage resultMessage = resultBuilder.build();
            byte[] resultByteArray = resultMessage.toByteArray();
            outputStream.write(resultByteArray);
        } catch (IOException exception) {
            ProtoHandler.writeError(exception, outputStream);
        } finally {
            /** outputStream 抛出的IO异常直接抛出 */
            outputStream.flush();
            outputStream.close();
        }
    }

    @GetMapping("/proto/all")
    public void protoStudentAll(HttpServletResponse response) throws IOException {
        /** getOutputStream 抛出的IO异常直接抛出 */
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            /** 参数校验 异常抛出等省略 */
            Student.Students students = studentService.queryAll();
            StudentProto.StudentsMessage.Builder studentsBuilder = StudentProto.StudentsMessage.newBuilder();
            ProtoHandler.formatMessage(ProtoHandler.getFormatter(),students,studentsBuilder);
            /** oneOf 字段设置对象 需要单独设置 */
            ResultProto.ResultMessage.Builder resultBuilder = ResultProto.ResultMessage.newBuilder();
            resultBuilder.mergeStudents(studentsBuilder.build()).setStatus(Result.OK)
                    .setMessage("成功").setTimestamp(new Date().getTime());
            ResultProto.ResultMessage resultMessage = resultBuilder.build();
            byte[] resultByteArray = resultMessage.toByteArray();
            outputStream.write(resultByteArray);
        } catch (IOException exception) {
            ProtoHandler.writeError(exception, outputStream);
        } finally {
            /** outputStream 抛出的IO异常直接抛出 */
            outputStream.flush();
            outputStream.close();
        }
    }

}

```

- > 教师Controller接口 <code>TeacherController.java</code>

```java
@CrossOrigin
@RestController
@RequestMapping("/rest/v0.1.0/teacher")
public class TeacherController {
    
    @Autowired
    private TeacherService teacherService;

    /**
     * RestFul 通过名称查询学生信息查询接口
     * @param name 学生名称
     * @return ResponseEntity
     */
    @GetMapping("/get/name")
    public ResponseEntity getTeacherByName(@RequestParam("name") String name) {
        Teacher result = teacherService.queryByTeacherName(name);
        return ResponseEntity.ok(Result.ok(result));
    }

    @GetMapping("/get/all")
    public ResponseEntity getTeacherAll() {
        Teacher.Teachers teachers = teacherService.queryAll();
        return ResponseEntity.ok(Result.ok(teachers.getTeachers()));
    }

    @PostMapping("/post/name")
    public ResponseEntity postTeacherByName(@RequestBody Filter filter) {
        String name = filter.getName();
        /** 参数校验 异常抛出等省略 */
        Teacher result = teacherService.queryByTeacherName(name);
        return ResponseEntity.ok(Result.ok(result));
    }


    @PostMapping("/proto/name")
    public void protoTeacherByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** getOutputStream 抛出的IO异常直接抛出 */
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            Filter filter = ProtoHandler.parserFilter(request);
            String name = filter.getName();
            /** 参数校验 异常抛出等省略 */
            Teacher result = teacherService.queryByTeacherName(name);
            TeacherProto.TeacherMessage.Builder teacherBuilder = TeacherProto.TeacherMessage.newBuilder();
            ProtoHandler.formatMessage(ProtoHandler.getFormatter(),result,teacherBuilder);
            /** oneOf 字段设置对象 需要单独设置 */
            ResultProto.ResultMessage.Builder resultBuilder = ResultProto.ResultMessage.newBuilder();
            resultBuilder.mergeTeacher(teacherBuilder.build()).setStatus(Result.OK)
                    .setMessage("成功").setTimestamp(new Date().getTime());
            ResultProto.ResultMessage resultMessage = resultBuilder.build();
            byte[] resultByteArray = resultMessage.toByteArray();
            outputStream.write(resultByteArray);
        } catch (IOException exception) {
            ProtoHandler.writeError(exception, outputStream);
        } finally {
            /** outputStream 抛出的IO异常直接抛出 */
            outputStream.flush();
            outputStream.close();
        }
    }

    @GetMapping("/proto/all")
    public void protoTeacherAll(HttpServletResponse response) throws IOException {
        /** getOutputStream 抛出的IO异常直接抛出 */
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            /** 参数校验 异常抛出等省略 */
            Teacher.Teachers teachers = teacherService.queryAll();
            TeacherProto.TeachersMessage.Builder teachersBuilder = TeacherProto.TeachersMessage.newBuilder();
            ProtoHandler.formatMessage(ProtoHandler.getFormatter(),teachers,teachersBuilder);
            /** oneOf 字段设置对象 需要单独设置 */
            ResultProto.ResultMessage.Builder resultBuilder = ResultProto.ResultMessage.newBuilder();
            resultBuilder.mergeTeachers(teachersBuilder.build()).setStatus(Result.OK)
                    .setMessage("成功").setTimestamp(new Date().getTime());
            ResultProto.ResultMessage resultMessage = resultBuilder.build();
            byte[] resultByteArray = resultMessage.toByteArray();
            outputStream.write(resultByteArray);
        } catch (IOException exception) {
            ProtoHandler.writeError(exception, outputStream);
        } finally {
            /** outputStream 抛出的IO异常直接抛出 */
            outputStream.flush();
            outputStream.close();
        }
    }
}
```


- > 班级Controller接口 <code>ClassController.java</code>

```java
@CrossOrigin
@RestController
@RequestMapping("/rest/v0.1.0/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * RestFul 通过名称查询班级信息查询接口
     * @param name 班级名称
     * @return ResponseEntity
     */
    @GetMapping("/get/name")
    public ResponseEntity getClassByName(@RequestParam("name") String name) {
        Class result = classService.queryByClassName(name);
        return ResponseEntity.ok(Result.ok(result));
    }

    @GetMapping("/get/all")
    public ResponseEntity getClassAll() {
        Class.Classes classes = classService.queryAll();
        return ResponseEntity.ok(Result.ok(classes.getClasses()));
    }

    @PostMapping("/post/name")
    public ResponseEntity postClassByName(@RequestBody Filter filter) {
        String name = filter.getName();
        /** 参数校验 异常抛出等省略 */
        Class result = classService.queryByClassName(name);
        return ResponseEntity.ok(Result.ok(result));
    }


    @PostMapping("/proto/name")
    public void protoClassByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** getOutputStream 抛出的IO异常直接抛出 */
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            Filter filter = ProtoHandler.parserFilter(request);
            String name = filter.getName();
            /** 参数校验 异常抛出等省略 */
            Class result = classService.queryByClassName(name);
            ClassProto.ClassMessage.Builder classBuilder = ClassProto.ClassMessage.newBuilder();
            ProtoHandler.formatMessage(ProtoHandler.getFormatter(),result,classBuilder);
            /** oneOf 字段设置对象 需要单独设置 */
            ResultProto.ResultMessage.Builder resultBuilder = ResultProto.ResultMessage.newBuilder();
            resultBuilder.mergeClass_(classBuilder.build()).setStatus(Result.OK)
                    .setMessage("成功").setTimestamp(new Date().getTime());
            ResultProto.ResultMessage resultMessage = resultBuilder.build();
            byte[] resultByteArray = resultMessage.toByteArray();
            outputStream.write(resultByteArray);
        } catch (IOException exception) {
            ProtoHandler.writeError(exception, outputStream);
        } finally {
            /** outputStream 抛出的IO异常直接抛出 */
            outputStream.flush();
            outputStream.close();
        }
    }

    @GetMapping("/proto/all")
    public void protoClassAll(HttpServletResponse response) throws IOException {
        /** getOutputStream 抛出的IO异常直接抛出 */
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            /** 参数校验 异常抛出等省略 */
            Class.Classes classes = classService.queryAll();
            ClassProto.ClassesMessage.Builder classesBuilder = ClassProto.ClassesMessage.newBuilder();
            ProtoHandler.formatMessage(ProtoHandler.getFormatter(),classes,classesBuilder);
            /** oneOf 字段设置对象 需要单独设置 */
            ResultProto.ResultMessage.Builder resultBuilder = ResultProto.ResultMessage.newBuilder();
            resultBuilder.mergeClasses(classesBuilder.build()).setStatus(Result.OK)
                    .setMessage("成功").setTimestamp(new Date().getTime());
            ResultProto.ResultMessage resultMessage = resultBuilder.build();
            byte[] resultByteArray = resultMessage.toByteArray();
            outputStream.write(resultByteArray);
        } catch (IOException exception) {
            ProtoHandler.writeError(exception, outputStream);
        } finally {
            /** outputStream 抛出的IO异常直接抛出 */
            outputStream.flush();
            outputStream.close();
        }
    }
}

```

&emsp;&emsp;在<code>cyan.simple.protobuf</code>包下新建<code>handler</code>包,抽取<code>Controller</code>里可复用代码。

- > 共用方法处理类 <code>ProtoHandler.java</code>

```java
@Component
public class ProtoHandler implements InitializingBean {

    @Autowired
    private FormatFactory formatFactory;

    private static ProtoHandler INSTANCE = null;

    private static ProtobufFormatter FORMATTER = null;

    public static ProtobufFormatter getFormatter() {
        return FORMATTER;
    }

    public static ProtoHandler getInstance() {
        return INSTANCE;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        INSTANCE = this;
        FORMATTER = formatFactory.createFormatter(FormatFactory.Formatter.JSON);
    }

    public static Filter parserFilter(HttpServletRequest request) throws IOException {
        byte[] bytes = StreamUtils.copyToByteArray(request.getInputStream());
        FilterProto.FilterMessage filterMessage = FilterProto.FilterMessage.parseFrom(bytes);
        /** 这里可以将 ProtobufFormatter 封装成静态工具类 */

        String jsonString = FORMATTER.printToString(filterMessage);
        Filter filter = JsonUtils.parserBean(jsonString, Filter.class);
        if (GeneralUtils.isEmpty(filter)) {
            throw new RuntimeException("json数据反序列化失败：" + jsonString);
        }
        return filter;
    }

    public static void formatMessage(ProtobufFormatter formatter, Object object, Message.Builder builder) throws IOException {
        String parserJson = JsonUtils.parserJson(object);
        if (GeneralUtils.isNotEmpty(parserJson)) {
            formatter.merge(new ByteArrayInputStream(parserJson.getBytes()), builder);
        }
    }

    public static void writeError(Throwable exception, ServletOutputStream outputStream) throws IOException {
        Result result = new Result(Result.ERROR, exception.getMessage());
        String parserJson = JsonUtils.parserJson(result);
        if (GeneralUtils.isNotEmpty(parserJson)) {
            /** outputStream 抛出的IO异常直接抛出 */
            outputStream.write(parserJson.getBytes());
        }
    }
}
```

## 八、Postman & Protoman测试接口

&emsp;&emsp;打开<code>Postman</code>工具，右上角新建<code>Environments</code>环境，名称为<code>Localhost:8080</code>,<code>Variable</code>的值为<code>HostPort</code>，
<code>Initial Value</code>的值为<code>localhost:8080</code>或者<code>127.0.0.1:8080</code>,确认保存。

&emsp;&emsp;新建<code>GET</code>接口测试，这里我们象征性的测试一下，保证<code>RESTFUL</code>接口及业务逻辑没问题。

- <code>Path</code>:
>
> <code>GET</code> <code>{{HostPort}}/rest/v0.1.0/teacher/get/name?name=李高数</code>
>
- <code>Response Body</code>:
```json
{
    "status": 200,
    "message": "成功",
    "data": {
        "name": "李高数",
        "age": 28,
        "sex": "男",
        "subject": "数学"
    },
    "timestamp": "2021-04-08 09:22:33"
}
```

- <code>Path</code>:
>
> <code>POST</code> <code>{{HostPort}}/rest/v0.1.0/teacher/post/name</code>
>

- <code>Request Body</code>:

```json
{
    "name": "李高数"
}
```

- <code>Response Body</code>:
```json
{
    "status": 200,
    "message": "成功",
    "data": {
        "name": "李高数",
        "age": 28,
        "sex": "男",
        "subject": "数学"
    },
    "timestamp": "2021-04-08 09:34:08"
}
```

&emsp;&emsp;打开<code>Protoman</code>工具，同理右上角新建<code>Environments</code>环境,名称为<code>Localhost:8080</code>,<code>Key</code>的值为<code>HostPort</code>,
<code>Value</code>的值为<code>http://localhost:8080</code>,确认保存。

&emsp;&emsp;选择<code>Collection1</code>右键<code>Edit name</code>,重命名为<code>protobuf-simple</code>,按<code>Enter</code>回车确认，继续右键<code>Manage .proto files</code>,
可以通过在<code>proto_path</code>输入<code>proto</code>文件所在的目录，点击左下角的<code>+</code>添加路径，打开文件选择器，选中所有<code>proto</code>文件，添加到<code>Protoman</code>工具，
勾选<code>Check All</code>选择所有文件路径，点击右下角<code>Build and Save</code>按钮。

&emsp;&emsp;选择<code>Request1</code>重命名<code>/class/proto/name</code>回车确认。

- <code>Path</code>:
>
> <code>POST</code> <code>{{HostPort}}/rest/v0.1.0/class/proto/name</code>
>

- <code>Request Body</code>选择<code>Protobuf</code>:
```text
Request Message: .cyan.simple.protobuf.proto.FilterMessage
.cyan.simple.protobuf.proto.FilterMessage {
    name: 三年2班
}

```
- <code>Expected Message</code>选择<code>.cyan.simple.protobuf.proto.ResultMessage</code>:
```text
On [200, 300): .cyan.simple.protobuf.proto.ResultMessage
On [300, ∞): .cyan.simple.protobuf.proto.ResultMessage
```

- 点击<code>Send</code>得到<code>Response Body</code>:
```text
.cyan.simple.protobuf.proto.ResultMessage {
    status: 200
    message: 成功
    timestamp: 1617777402089
    data: 
        class: .cyan.simple.protobuf.proto.ClassMessage {
            name: 三年2班
            size: 45
            teachers:
                .cyan.simple.protobuf.proto.TeacherMessage {
                    name: 李高数
                    age: 28
                    sex: 男
                    subject: 数学
                }
            students:
                .cyan.simple.protobuf.proto.StudentMessage {
                    name: 王小明
                    age: 15
                    sex: 男
                }
        }
}
```

&emsp;&emsp;新建<code>Request</code>重命名<code>/class/proto/all</code>回车确认。

- <code>Path</code>:
>
> <code>GET</code> <code>{{HostPort}}/rest/v0.1.0/class/proto/all</code>
>

- <code>Request Body</code>选择<code>None</code>:

- <code>Expected Message</code>选择<code>.cyan.simple.protobuf.proto.ResultMessage</code>:
```text
On [200, 300): .cyan.simple.protobuf.proto.ResultMessage
On [300, ∞): .cyan.simple.protobuf.proto.ResultMessage
```

- 点击<code>Send</code>得到<code>Response Body</code>:
```text
.cyan.simple.protobuf.proto.ResultMessage {
    status: 200
    message: 成功
    timestamp: 1617761894552
    data:
        classes: .cyan.simple.protobuf.proto.ClassesMessage {
            classes:
                .cyan.simple.protobuf.proto.ClassMessage {
                    name: 三年1班
                    size: 45
                    teachers:
                        .cyan.simple.protobuf.proto.TeacherMessage {
                            name: 王英语
                            age: 31
                            sex: 女
                            subject: 英语
                        }
                    students:
                        .cyan.simple.protobuf.proto.StudentMessage {
                            name: 张小花
                            age: 14
                            sex: 女
                        }
                }
                .cyan.simple.protobuf.proto.ClassMessage {
                    name: 三年2班
                    size: 45
                    teachers:
                        .cyan.simple.protobuf.proto.TeacherMessage {
                            name: 李高数
                            age: 28
                            sex: 男
                            subject: 数学
                        }
                    students:
                        .cyan.simple.protobuf.proto.StudentMessage {
                            name: 王小明
                            age: 15
                            sex: 男
                        }
                }
        }
}
```

> 其他详细的测试可以参考[protobuf-simple](https://github.com/Cyanss/protobuf-simple)下的<code>doc</code>文件夹中的<code>protobuf-simple.json</code>，通过<code>Protoman</code>工具导入到<code>Collections</code>中。

## 九、附录:

- [protobuf-simple](https://github.com/Cyanss/protobuf-simple)

- [protobuf GitHub](https://github.com/protocolbuffers/protobuf)

- [Protoman Github](https://github.com/haizhibin1989/Protoman)

- [Language Guide (proto3)](https://developers.google.cn/protocol-buffers/docs/proto3)

- [Protobuf 语言指南（proto3）](https://blog.csdn.net/sanshengshui/article/details/82950531)
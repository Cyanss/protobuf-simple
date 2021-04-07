## 一、安装配置

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

## 二、项目测试

&emsp;&emsp;打开IDEA新建<code>protobuf-simple</code>项目，删除暂时用不到的<code>.mvn</code>、<code>HELP.md</code>、
<code>mvnw</code>、<code>mvnw.cmd</code>和<code>resources</code>目录下的<code>static</code>、<code>templates</code>、<code>test</code>文件，
引入我们需要的Mave依赖，POM.xml文件如下：
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
&emsp;&emsp;

&emsp;&emsp;
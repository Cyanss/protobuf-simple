package cyan.simple.protobuf.controller;

import cyan.simple.protobuf.handler.ProtoHandler;
import cyan.simple.protobuf.model.Result;
import cyan.simple.protobuf.model.Student;
import cyan.simple.protobuf.model.filter.Filter;
import cyan.simple.protobuf.model.proto.ResultProto;
import cyan.simple.protobuf.model.proto.StudentProto;
import cyan.simple.protobuf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * <p>StudentController</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 10:41 2021/4/7
 */
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

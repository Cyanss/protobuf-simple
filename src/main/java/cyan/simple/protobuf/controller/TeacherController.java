package cyan.simple.protobuf.controller;

import cyan.simple.protobuf.handler.ProtoHandler;
import cyan.simple.protobuf.model.Result;
import cyan.simple.protobuf.model.Teacher;
import cyan.simple.protobuf.model.filter.Filter;
import cyan.simple.protobuf.model.proto.ResultProto;
import cyan.simple.protobuf.model.proto.TeacherProto;
import cyan.simple.protobuf.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * <p>TeacherController</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 14:26 2021/4/7
 */
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

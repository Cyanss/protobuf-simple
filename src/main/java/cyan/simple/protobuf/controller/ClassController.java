package cyan.simple.protobuf.controller;

import cyan.simple.protobuf.handler.ProtoHandler;
import cyan.simple.protobuf.model.Class;
import cyan.simple.protobuf.model.Result;
import cyan.simple.protobuf.model.filter.Filter;
import cyan.simple.protobuf.model.proto.ClassProto;
import cyan.simple.protobuf.model.proto.ResultProto;
import cyan.simple.protobuf.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * <p>ClassController</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 8:58 2021/4/6
 */
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

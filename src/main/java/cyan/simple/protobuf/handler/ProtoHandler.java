package cyan.simple.protobuf.handler;

import com.google.protobuf.Message;
import com.googlecode.protobuf.format.FormatFactory;
import com.googlecode.protobuf.format.ProtobufFormatter;
import cyan.simple.protobuf.model.Result;
import cyan.simple.protobuf.model.filter.Filter;
import cyan.simple.protobuf.model.proto.FilterProto;
import cyan.simple.protobuf.util.GeneralUtils;
import cyan.simple.protobuf.util.JsonUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * <p>ProtoHandler</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 11:09 2021/4/7
 */
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

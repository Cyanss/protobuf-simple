package cyan.simple.protobuf.configure;

import com.googlecode.protobuf.format.FormatFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>ProtobufAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 10:49 2021/4/6
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"cyan.simple.protobuf"})
public class ProtobufAutoConfigure {
    public ProtobufAutoConfigure() {
        log.debug("================= rest-toolkit-starter initiated ！ ===================");
    }

    @Bean
    public FormatFactory formatFactory() {
        return new FormatFactory();
    }
}

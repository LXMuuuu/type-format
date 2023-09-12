package cn.lxmuuuu.typeformat.config;

import cn.lxmuuuu.typeformat.config.serializer.LongToStringSerializer;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


@Configuration
public class JacksonConfig {

    /**
     * 配置Jackson进行全局返回序列化处理。
     *
     * @Bean
     * * 告诉Spring这是一个需要被装载的Bean。
     * @Primary
     * * 声明这个Bean是高优先级的。
     * @ConditionalOnMissingBean(ObjectMapper.class)
     * * 表示ObjectMapper类型的Bean只能有一个实现。出现第二个时会进行报错处理，因此多个实现需要搭配@Primary来标明优先级。
     *
     * @param builder
     * @return
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectFormatMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // 全局配置序列化返回 JSON 处理
        SimpleModule simpleModule = new SimpleModule();
        // 添加序列化规则，当为Long类型时进行处理，由自定义序列化配置StringSerializer实现。
        simpleModule.addSerializer(Long.class, new LongToStringSerializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}

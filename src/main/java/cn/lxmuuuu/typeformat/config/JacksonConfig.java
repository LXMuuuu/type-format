package cn.lxmuuuu.typeformat.config;

import cn.lxmuuuu.typeformat.anno.StringFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.Objects;

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
        simpleModule.addSerializer(Long.class, new StringSerializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    /**
     * 自定义序列化规则
     *
     * 继承JsonSerializer，Jackson序列化会触发此方法，对值进行修改。
     * 实现ContextualSerializer 解析自定义注解，依赖判断该值是否被标识注解@StringFormat所标记。
     * * 也可以对注解@StringFormat进行一个默认值处理，以此来拿到某些特定的值，用于特殊场景。
     *
     */
    private static class StringSerializer extends JsonSerializer<Long> implements ContextualSerializer {
        @Override
        public void serialize(Long value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if (value != null) {
                gen.writeString(value.toString());
            }
        }

        @Override
        public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
            if (beanProperty != null) {
                if (Objects.equals(beanProperty.getType().getRawClass(), Long.class)) {
                    boolean isStringFormat = beanProperty.getMember().hasAnnotation(StringFormat.class);
                    if (isStringFormat) {
                        return new StringSerializer();
                    }
                }
            }
            return serializerProvider.findNullValueSerializer(beanProperty);
        }
    }


}

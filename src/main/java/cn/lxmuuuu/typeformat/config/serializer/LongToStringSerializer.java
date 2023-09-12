package cn.lxmuuuu.typeformat.config.serializer;

import cn.lxmuuuu.typeformat.anno.StringFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 自定义序列化规则
 *
 * 继承JsonSerializer，Jackson序列化会触发此方法，对值进行修改。
 * 实现ContextualSerializer 解析自定义注解，依赖判断该值是否被标识注解@StringFormat所标记。
 * * 也可以对注解@StringFormat进行一个默认值处理，以此来拿到某些特定的值，用于特殊场景。
 *
 */
public class LongToStringSerializer extends JsonSerializer<Long> implements ContextualSerializer {

    /**
     * 目前发现问题：当序列化对象为Map类型，无法区分Key，Value。只要内容包含Long类型并且被@StringFormat标识，就会被转为String类型
     * * 因此并不推荐使用Map类型作为输出对象并且使用@StringFormat
     * @param value
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (value != null) {
            jsonGenerator.writeString(value.toString());
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            boolean isStringFormat = beanProperty.getMember().hasAnnotation(StringFormat.class);
            if (isStringFormat) {
                return new LongToStringSerializer();
            }
        }
        // 未被@StringFormat标注的Long类型属性走默认值
        return new JsonSerializer<Long>() {
            @Override
            public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                if(value != null)
                    jsonGenerator.writeNumber(value);
            }
        };
    }
}

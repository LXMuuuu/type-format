package cn.lxmuuuu.typeformat.config.serializer;

import cn.lxmuuuu.typeformat.anno.IFormat;
import cn.lxmuuuu.typeformat.anno.IFormatDateTypeEnum;
import cn.lxmuuuu.typeformat.anno.IFormatEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToStringSerializer extends JsonSerializer<Date> implements ContextualSerializer {

    private IFormatDateTypeEnum dateTypeEnum;

    public DateToStringSerializer(){}

    public DateToStringSerializer(IFormatDateTypeEnum dateTypeEnum){
        this.dateTypeEnum = dateTypeEnum;
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (null != date)
            jsonGenerator.writeString(format(date));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            boolean isFormat = beanProperty.getMember().hasAnnotation(IFormat.class);
            if (isFormat) {
                boolean isValidity = beanProperty.getAnnotation(IFormat.class).type().compare(IFormatEnum.Date_To_String);
                if (isValidity) {
                    return new DateToStringSerializer(beanProperty.getAnnotation(IFormat.class).dateType());
                }
            }
        }
        // 未被@StringFormat标注的Date类型属性走默认值
        return new DateSerializer();
    }

    private String format(Date target) {
        if (dateTypeEnum.getKey() < 9) {
            DateFormat dateFormat = new SimpleDateFormat(dateTypeEnum.getValue());
            return dateFormat.format(target);
        } else {
            if (dateTypeEnum.getKey() == 9) {
                return String.valueOf(target.getTime());
            }
        }
        return null;
    }
}

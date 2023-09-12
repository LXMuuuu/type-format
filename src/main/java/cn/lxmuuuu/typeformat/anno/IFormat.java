package cn.lxmuuuu.typeformat.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ FIELD, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface IFormat {

    /**
     * 字段序列化类型
     * @return
     */
    IFormatEnum type() default IFormatEnum.Not_Format;

    /**
     * 如果IFormatEnum类型为：Date_To_String..可以选择序列化类型
     * @return
     */
    IFormatDateTypeEnum dateType() default IFormatDateTypeEnum.Full;

}

package cn.lxmuuuu.typeformat.anno;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

@Documented
@Target({ FIELD, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface StringFormat {
}

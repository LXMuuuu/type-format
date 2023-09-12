package cn.lxmuuuu.typeformat.request;

import cn.lxmuuuu.typeformat.anno.StringFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class TestRequest implements Serializable {

    @StringFormat
    private Long id;

}

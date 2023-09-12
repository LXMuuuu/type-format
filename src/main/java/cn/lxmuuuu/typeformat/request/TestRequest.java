package cn.lxmuuuu.typeformat.request;

import cn.lxmuuuu.typeformat.anno.StringFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class TestRequest implements Serializable {

    @StringFormat
    private Long id;

    @StringFormat
    private List<Long> ids;

    @StringFormat
    private Map<Long, String> idMap;

}

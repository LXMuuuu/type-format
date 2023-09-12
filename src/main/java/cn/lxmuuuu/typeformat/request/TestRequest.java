package cn.lxmuuuu.typeformat.request;

import cn.lxmuuuu.typeformat.anno.IFormatDateTypeEnum;
import cn.lxmuuuu.typeformat.anno.IFormatEnum;
import cn.lxmuuuu.typeformat.anno.IFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TestRequest implements Serializable {

    @IFormat(type = IFormatEnum.Long_To_String)
    private Long id;

//    @IFormat(type = IFormatEnum.Date_To_String, dateType = IFormatDateTypeEnum.Convert_Timestamp)
    private Date testDate;

}

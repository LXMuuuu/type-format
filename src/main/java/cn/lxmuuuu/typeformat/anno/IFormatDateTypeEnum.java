package cn.lxmuuuu.typeformat.anno;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IFormatDateTypeEnum {

    Full(0, "yyyy-MM-dd HH:mm:ss"),
    Only_Date(1, "yyyy-MM-dd"),
    Only_Time(2, "HH:mm:ss"),
    Only_Year(3, "yyyy"),
    Only_Month(4, "MM"),
    Only_Day(5, "dd"),
    Only_Hour(6, "HH"),
    Only_Minute(7, "mm"),
    Only_Second(8, "ss"),
    Convert_Timestamp(9, "Convert_Timestamp"),
    ;

    int key;

    String value;

}

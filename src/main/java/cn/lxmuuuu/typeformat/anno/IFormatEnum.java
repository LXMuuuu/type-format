package cn.lxmuuuu.typeformat.anno;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IFormatEnum {

    Not_Format(0, "NotFormat"),
    Long_To_String(1, "LongToString"),
    Date_To_String(2, "DateToString"),
    ;

    final int key;

    final String value;

    public boolean compare(IFormatEnum target) {
        if (null != target) {
            return this.getKey() == target.getKey() && this.getValue().equals(target.getValue());
        }
        return false;
    }

}

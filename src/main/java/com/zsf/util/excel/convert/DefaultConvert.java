package com.zsf.util.excel.convert;

/**
 *  列值转化
 */
public class DefaultConvert implements ColumnConvert<String, String> {

    @Override
    public String convert(String o) {
        return o;
    }
}

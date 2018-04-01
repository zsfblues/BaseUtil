package com.zsf.util.excel;


import com.zsf.util.excel.convert.ColumnConvert;
import com.zsf.util.excel.convert.DefaultConvert;

/**
 * 模板列标题
 */
public class ColumnMeta {

    /**
     * 列标题
     */
    private String title;

    /**
     * 该列对应的键值,用于从map获取value,写到单元格中
     */
    private String key;

    /**
     * 转化的类型转换
     */
    private ColumnConvert<String, String> columnConvert = new DefaultConvert();

    /**
     * 列排序
     * 暂时未使用
     */
    private int sort;

    public ColumnMeta(String title, String key) {
        this.title = title;
        this.key = key;
    }

    public ColumnMeta(String title, String key, ColumnConvert columnConvert) {
        this.title = title;
        this.key = key;
        this.columnConvert = columnConvert;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String convert(String o) {
        return this.columnConvert.convert(o);
    }

}

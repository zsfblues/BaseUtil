package com.zsf.util.excel.convert;

/**
 * 列值转化。主要针对业务数据的处理
 */
public interface ColumnConvert<V, T> {

    /**
     * 值的转换
     * @param v
     * @return
     */
    T convert(V v);
}

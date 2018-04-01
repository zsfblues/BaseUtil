package com.zsf.util;

import com.zsf.entity.BaseEntity;
import org.springframework.beans.BeanUtils;

/**
 * Created on 2017/11/4.
 *
 * @author zhoushengfan
 */
public interface BaseVO<K extends BaseVO, T extends BaseEntity> {

    /**
     * 将DO对象中的属性重新封装到VO对象中
     * @param obj DO对象
     * @return VO对象
     */
    default K forVO(T obj){
        if (obj != null) {
            BeanUtils.copyProperties(obj, this);
        }
        return (K) this;
    }

    /**
     *
     * @param vo 待转换的vo对象，记得在转换前需要先剔除存在vo中但不存在DO中字段
     * @return
     */
    default T forDO(K vo){
        throw new UnsupportedOperationException("unsupported operation!");
    }
}
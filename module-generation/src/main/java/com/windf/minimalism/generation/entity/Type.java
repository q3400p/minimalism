package com.windf.minimalism.generation.entity;

import com.windf.core.entity.Entitiable;

public interface Type extends Entitiable {
    /**
     * 获取类型的编码
     * 这个编码，可以用于模板中填写
     * @return
     */
    String getTypeCode();

    /**
     * 获取类型的名称
     * 用于识别名称
     * @return
     */
    String getTypeName();
}

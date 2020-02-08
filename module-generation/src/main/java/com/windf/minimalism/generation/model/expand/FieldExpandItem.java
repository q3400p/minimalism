package com.windf.minimalism.generation.model.expand;

import com.windf.minimalism.generation.entity.Field;

/**
 * 实体属性的拓属性
 */
public interface FieldExpandItem extends ExpandItem {
    /**
     * 获取拓展属性，用于设置值
     * @return
     */
    Field getField();
}

package com.windf.minimalism.generation.model.expand;

import com.windf.minimalism.generation.entity.Method;

/**
 * 实体方法的拓属性
 */
public interface MethodExpandItem extends ExpandItem {
    /**
     * 获取拓展属性，用于设置值
     * @return
     */
    Method getMethod();
}

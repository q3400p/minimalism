package com.windf.minimalism.generation.model.expand;

import com.windf.minimalism.generation.entity.Entity;

/**
 * 实体的拓属性
 */
public interface EntityExpandItem extends ExpandItem {
    /**
     * 获取拓展属性，用于设置值
     * @return
     */
    Entity getEntity();
}

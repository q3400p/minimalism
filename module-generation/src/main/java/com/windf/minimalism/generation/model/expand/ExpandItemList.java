package com.windf.minimalism.generation.model.expand;

import java.util.List;

/**
 * 拓展的插槽列表，获取该该实体的所有插槽
 */
public interface ExpandItemList<T extends ExpandItem> {
    /**
     * 获取拓展列表
     */
    List<T> getExpandItemList();
}

package com.windf.minimalism.generation.model.expand;

import java.util.List;

/**
 * 拓展的插槽列表，获取该该实体的所有插槽
 */
public interface ExpandSlot {
    /**
     * 获取拓展列表
     */
    List<ExpandItem> getExpandItemList();

    /**
     * 根据code加载拓展属性
     * @param code
     * @return
     */
    ExpandItem getExpandItem(String code);
}

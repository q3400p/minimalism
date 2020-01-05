package com.windf.minimalism.generation.entity;

import java.util.List;

/**
 * 拓展的插槽
 * 用于其他类型属性的拓展,并且设置值
 */
public interface ExpandSlot {
    /**
     * 获取所有的拓展项
     * @return
     */
    List<ExpandItem> getExpandItems();

    /**
     * 批量设置拓展属性
     * @param expandItems
     */
    void setExpandItems(List<ExpandItem> expandItems);

    /**
     * 获取每个拓展项
     * @param code
     * @return
     */
    ExpandItem getExpandItem(String code);

    /**
     * 修改某个拓展向的值
     * @param code
     * @param expandItem
     */
    void setExpandItem(String code, ExpandItem expandItem);
}

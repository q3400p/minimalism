package com.windf.minimalism.generation.model.expand;

/**
 * 拓展的插槽
 * 用于对拓展属性进行设置和获取属性的值
 */
public interface ExpandValue {
    /**
     * 设置拓展属性的值
     * @param itemCode
     * @param value
     */
    void setExpandValue(String itemCode, Object value);

    /**
     * 获取拓展项的值
     * @param code
     * @return
     */
    Object getExpandValue(String code);
}

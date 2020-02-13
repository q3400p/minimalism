package com.windf.minimalism.generation.model.expand;

import com.windf.minimalism.generation.entity.Type;

public interface ExpandItem<T extends ExpandSlot>  {

    /**
     * 获取拓展项的名称
     * @return
     */
    String getName();

    /**
     * 获取拓展项的编号，用于模板中使用
     * @return
     */
    String getCode();

    /**
     * 获取拓展项的类型，用于展示和设置
     * @return
     */
    Type getType();

    /**
     * 属性是否是必填的
     * @return
     */
    boolean isRequested();

    /**
     * 获取拓展想的默认值
     * @return
     */
    Object getDefaultValue(T expandSlot);

    /**
     * 获取要拓展的项目
     * @return
     */
    Class<T> getExpandType();
}

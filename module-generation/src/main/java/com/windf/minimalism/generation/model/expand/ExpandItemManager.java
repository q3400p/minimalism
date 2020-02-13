package com.windf.minimalism.generation.model.expand;

import java.util.*;

public interface ExpandItemManager {

    /**
     * 获取某个一行的拓展类型的所有属性
     * @return
     */
    List<ExpandItem> getExpandItemList();

    /**
     * 获取某个拓展属性
     * @param code
     * @return
     */
    ExpandItem getExpandItem(String code);
}

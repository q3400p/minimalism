package com.windf.minimalism.generation.model.expand;

import java.util.*;

public class ExpandItemListProcess {

    private static ExpandItemListProcess expandItemListProcess = new ExpandItemListProcess();

    public static ExpandItemListProcess getInstance() {
        return expandItemListProcess;
    }

    // 根据接口id，存储拓展属性的列表
    private Map<Class, List<ExpandItem>> expandItemListMap = new HashMap<>();

    // 根据code存储拓展属性
    private Map<Class, Map<String, ExpandItem>> expandCodeMap = new HashMap<>();

    private ExpandItemListProcess() {
    }

    /**
     * 获取某个一乐行的拓展类型的所有属性
     * @param expandItemClass
     * @param <T>
     * @return
     */
    public <T extends ExpandItem> List<ExpandItem> getExpandItemList(Class<T> expandItemClass) {

        // 加载
        this.load(expandItemClass);

        // 取值
        return expandItemListMap.get(expandItemClass);
    }

    /**
     * 获取某个拓展属性
     * @param expandItemClass
     * @param code
     * @param <T>
     * @return
     */
    public <T extends ExpandItem> ExpandItem getExpandItem(Class<T> expandItemClass, String code) {

        // 获取拓展属性，如果没有的话加载
        Map<String, ExpandItem> expandItemMap = this.expandCodeMap.get(expandItemClass);
        if (expandItemMap == null) {
            this.reload(expandItemClass);
        }

        // 获取配置
        return (T) expandItemMap.get(code);
    }

    /**
     * 加载ExpandItem
     * @param expandItemClass
     * @param <T>
     * @return
     */
    public <T extends ExpandItem> void load(Class<T> expandItemClass) {
        if (expandCodeMap.get(expandItemClass) == null) {
            this.reload(expandItemClass);
        }
    }

    /**
     * 重新加载ExpandItem
     * @param expandItemClass
     * @return
     */
    public <T extends ExpandItem> void reload(Class<T> expandItemClass) {
        // 加载所有的接口
        ServiceLoader<T> serviceLoadList = ServiceLoader.load(expandItemClass);

        // 整理拓展属性，放到列表和map中
        List<ExpandItem> expandItems = new ArrayList<>();
        Map<String, ExpandItem> codeExpandMap = new HashMap<>();
        if (serviceLoadList != null) {
            for (T expandItem : serviceLoadList) {
                expandItems.add(expandItem);
                codeExpandMap.put(expandItem.getCode(), expandItem);
            }
        }

        // 存放到容器中
        expandItemListMap.put(expandItemClass, expandItems);
        expandCodeMap.put(expandItemClass, codeExpandMap);
    }
}

package com.windf.minimalism.generation.model.expand;

import com.windf.core.util.BeanUtil;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.model.template.CodeTemplateHandler;

import java.util.*;

public class ExpandItemManagerProcess {

    private static ExpandItemManagerProcess expandItemListProcess = new ExpandItemManagerProcess();

    public static ExpandItemManagerProcess getInstance() {
        return expandItemListProcess;
    }

    // 根据接口id，存储拓展属性的列表
    private Map<Class, List<ExpandItem>> expandItemListMap = new HashMap<>();

    // 根据code存储拓展属性
    private Map<Class, Map<String, ExpandItem>> expandCodeMap = new HashMap<>();

    private ExpandItemManagerProcess() {
    }

    /**
     * 获取某个一行的拓展类型的所有属性
     * @param expandSlotClass
     * @return
     */
    public List<ExpandItem> getExpandItemList(Class expandSlotClass) {

        // 加载
        this.load(expandSlotClass);

        // 取值
        return expandItemListMap.get(expandSlotClass);
    }

    /**
     * 获取某个拓展属性
     * @param expandSlotClass
     * @param code
     * @return
     */
    public ExpandItem getExpandItem(Class expandSlotClass, String code) {

        // 获取拓展属性，如果没有的话加载
        Map<String, ExpandItem> expandItemMap = this.expandCodeMap.get(expandSlotClass);
        if (expandItemMap == null) {
            this.reload(expandSlotClass);
        }

        // 获取配置
        return expandItemMap.get(code);
    }

    /**
     * 获取所有映射关系
     * @param codeTemplateHandler
     * @param expandSlot
     * @return
     */
    public Map<String, Object> getExpandedMap(CodeTemplateHandler codeTemplateHandler, ExpandSlot expandSlot) {
        Map<String, Object> result = new HashMap<>();

        // 设置所有的字段到map中
        result.putAll(BeanUtil.getAllGetterMethods(expandSlot));

        for (ExpandItem expandItem : codeTemplateHandler.getExpandItems()) {
            // 只获取特定类型的数据
            if (expandSlot.getClass() != expandItem.getExpandType()) {
                continue;
            }

            // 获取拓展类中的值，如果没有，获取默认值
            Object expandValue = expandSlot.getExpandValue(expandItem.getCode());
            if (expandValue == null) {
                expandValue = expandItem.getDefaultValue(expandSlot);
            }

            // 设置值
            result.put(expandItem.getCode(), expandValue);
        }

        return result;
    }

    /**
     * 加载ExpandItem
     * @param expandSlotClass
     * @param <T>
     * @return
     */
    protected <T extends ExpandItem> void load(Class<T> expandSlotClass) {
        if (expandCodeMap.get(expandSlotClass) == null) {
            this.reload(expandSlotClass);
        }
    }

    /**
     * 重新加载ExpandItem
     * @param expandSlotClass
     * @return
     */
    protected <T extends ExpandItem> void reload(Class<T> expandSlotClass) {
        // 加载所有的接口
        ServiceLoader<T> serviceLoadList = ServiceLoader.load(expandSlotClass);

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
        expandItemListMap.put(expandSlotClass, expandItems);
        expandCodeMap.put(expandSlotClass, codeExpandMap);
    }
}

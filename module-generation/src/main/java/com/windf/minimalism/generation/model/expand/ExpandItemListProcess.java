package com.windf.minimalism.generation.model.expand;

import java.util.*;

public class ExpandItemListProcess {

    private static ExpandItemListProcess expandItemListProcess = new ExpandItemListProcess();

    public static ExpandItemListProcess getInstance() {
        return expandItemListProcess;
    }

    private Map<String, List<ExpandItem>> expandItemMap = new HashMap<>();

    private ExpandItemListProcess() {
    }

    public <T extends ExpandItem> List<T> getExpandItemList(Class<T> expandItem) {

        List expandItems = expandItemMap.get(expandItem.getName());

        if (expandItems == null) {
            expandItems = this.reload(expandItem);
            expandItemMap.put(expandItem.getName(), expandItems);
        }

        return expandItems;
    }

    /**
     * 重新加载
     * @return
     */
    public <T extends ExpandItem> List<T> reload(Class<T> expandItem) {
        ServiceLoader<T> handlerList = ServiceLoader.load(expandItem);

        List<T> handlers = new ArrayList<>();
        if (handlerList != null) {
            for (T handler : handlerList) {
                handlers.add(handler);

            }
        }

        return handlers;
    }
}

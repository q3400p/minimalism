package com.windf.minimalism.generation.entity;

import com.windf.core.entity.BaseEntity;
import com.windf.minimalism.generation.model.expand.ExpandItem;
import com.windf.minimalism.generation.model.expand.ExpandSlot;

import java.io.Serializable;
import java.util.*;

public class BaseModel extends BaseEntity implements ExpandSlot, Serializable {
    /**
     * id之间隔离的字符串
     */
    public static final String ID_POINT = ".";

    // TODO createTime和baseEntity中的冲突了
    private Date createTime;
    private Date updateTime;
    private String description;
    private LinkedHashMap<String, ExpandItem> expandItemMap = new LinkedHashMap<>();

    @Override
    public List<ExpandItem> getExpandItems() {
        return new ArrayList<>(expandItemMap.values());
    }

    @Override
    public void setExpandItems(List<ExpandItem> expandItems) {
        expandItemMap = new LinkedHashMap<>();
        for (ExpandItem expandItem : expandItems) {
            expandItemMap.put(expandItem.getCode(), expandItem);
        }
    }

    @Override
    public ExpandItem getExpandItem(String code) {
        return expandItemMap.get(code);
    }

    @Override
    public void setExpandItem(String code, ExpandItem expandItem) {
        expandItemMap.put(code, expandItem);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

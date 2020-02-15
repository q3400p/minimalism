package com.windf.minimalism.generation.entity;

import com.windf.core.entity.BaseEntity;
import com.windf.minimalism.generation.model.expand.ExpandSlot;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseModel extends BaseEntity implements ExpandSlot, Serializable {
    /**
     * id之间隔离的字符串
     */
    public static final String ID_POINT = ".";

    private String description;
    private Map<String, Object> expand = new HashMap<>();

    @Override
    public void setExpandValue(String itemCode, Object value) {
        expand.put(itemCode, value);
    }

    @Override
    public Object getExpandValue(String code) {
        return expand.get(code);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getExpand() {
        return expand;
    }

    public void setExpand(Map<String, Object> expand) {
        this.expand = expand;
    }
}

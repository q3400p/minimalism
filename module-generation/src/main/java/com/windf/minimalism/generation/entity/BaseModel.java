package com.windf.minimalism.generation.entity;

import com.windf.core.entity.BaseEntity;
import com.windf.minimalism.generation.model.expand.ExpandSlot;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseModel extends BaseEntity implements ExpandSlot, Serializable {
    /**
     * id之间隔离的字符串
     */
    public static final String ID_POINT = ".";

    // TODO createTime和baseEntity中的冲突了
    private Date createTime;
    private Date updateTime;
    private String description;
    private Map<String, Object> expandValueMap = new HashMap<>();

    @Override
    public void setExpandValue(String itemCode, Object value) {
        expandValueMap.put(itemCode, value);
    }

    @Override
    public Object getExpandValue(String code) {
        return expandValueMap.get(code);
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

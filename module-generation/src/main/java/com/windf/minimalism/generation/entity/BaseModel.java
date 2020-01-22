package com.windf.minimalism.generation.entity;

import com.windf.core.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BaseModel extends BaseEntity implements ExpandSlot, Serializable {
    /**
     * id之间隔离的字符串
     */
    public static final String ID_POINT = ".";

    private Date createTime;
    private Date updateTime;
    private String description;

    @Override
    public List<ExpandItem> getExpandItems() {
        return null;
    }

    @Override
    public void setExpandItems(List<ExpandItem> expandItems) {

    }

    @Override
    public ExpandItem getExpandItem(String code) {
        return null;
    }

    @Override
    public void setExpandItem(String code, ExpandItem expandItem) {

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

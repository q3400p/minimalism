package com.windf.minimalism.generation.entity;

public class Field extends BaseModel {
    private String code;
    private String name;
    /**
     * 是否是唯一的，也可以和几个字段一起唯一，只要uniqueGroup相同
     */
    private String uniqueGroup;
    /**
     * 是否不为空
     */
    private boolean notNull;
    private Entity entity;
    private Type type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUniqueGroup() {
        return uniqueGroup;
    }

    public void setUniqueGroup(String uniqueGroup) {
        this.uniqueGroup = uniqueGroup;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }
}

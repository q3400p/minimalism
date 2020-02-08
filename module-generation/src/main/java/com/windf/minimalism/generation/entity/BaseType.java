package com.windf.minimalism.generation.entity;

public abstract class BaseType implements Type {
    /**
     * 获取LangType的类型
     * @return
     */
    public abstract LangType getLangType();

    @Override
    public String getId() {
        return getLangType().getId();
    }

    @Override
    public String getTypeCode() {
        return getLangType().getCode();
    }

    @Override
    public String getTypeName() {
        return getLangType().getName();
    }
}
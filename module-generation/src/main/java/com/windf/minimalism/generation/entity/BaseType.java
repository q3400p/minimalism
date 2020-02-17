package com.windf.minimalism.generation.entity;

public abstract class BaseType extends Type {
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
    public String getCode() {
        return getLangType().getCode();
    }

    @Override
    public String getName() {
        return getLangType().getName();
    }
}

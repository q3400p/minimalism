package com.windf.minimalism.generation.entity.type;

import com.windf.minimalism.generation.entity.Type;

public abstract class BaseType implements Type {
    private LangType langType;

    /**
     * 获取LangType的类型
     * @return
     */
    public abstract LangType getLangType();

    @Override
    public String getId() {
        return getTypeCode();
    }

    @Override
    public String getTypeCode() {
        return langType.getCode();
    }

    @Override
    public String getTypeName() {
        return langType.getName();
    }
}

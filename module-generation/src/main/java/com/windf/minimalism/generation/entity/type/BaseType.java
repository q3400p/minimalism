package com.windf.minimalism.generation.entity.type;

import com.windf.minimalism.generation.entity.Type;

public abstract class BaseType implements Type {
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
        return getLangType().getCode();
    }

    @Override
    public String getTypeName() {
        return getLangType().getName();
    }
}

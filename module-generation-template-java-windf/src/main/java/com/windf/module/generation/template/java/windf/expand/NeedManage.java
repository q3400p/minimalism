package com.windf.module.generation.template.java.windf.expand;

import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.EntityExpandItem;

public class NeedManage implements EntityExpandItem {
    @Override
    public String getName() {
        return "是否需要管理";
    }

    @Override
    public String getCode() {
        return "needManage";
    }

    @Override
    public Type getType() {
        return LangType.BOOLEAN.getType();
    }

    @Override
    public Object getDefaultValue() {
        return false;
    }

    @Override
    public boolean isRequested() {
        return false;
    }
}

package com.windf.minimalism.generation.template.java.expand;

import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.EntityExpandItem;

public class EntityName implements EntityExpandItem {
    @Override
    public String getName() {
        return "实体名称";
    }

    @Override
    public String getCode() {
        return "entityName";
    }

    @Override
    public Type getType() {
        return LangType.STRING.getType();
    }

    @Override
    public Object getDefaultValue() {
        return null;
    }

    @Override
    public boolean isRequested() {
        return false;
    }
}

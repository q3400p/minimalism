package com.windf.minimalism.generation.template.java.expand;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class ClassCode implements ExpandItem<Entity> {
    @Override
    public String getName() {
        return "类名";
    }

    @Override
    public String getCode() {
        return "classCode";
    }

    @Override
    public Type getType() {
        return LangType.Field.getType();
    }

    @Override
    public boolean isRequested() {
        return false;
    }

    @Override
    public Object getDefaultValue(Entity expandSlot) {
        return StringUtil.firstLetterUppercase(expandSlot.getCode());
    }

    @Override
    public Class<Entity> getExpandType() {
        return Entity.class;
    }
}

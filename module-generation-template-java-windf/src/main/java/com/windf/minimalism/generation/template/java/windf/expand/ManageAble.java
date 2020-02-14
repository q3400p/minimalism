package com.windf.minimalism.generation.template.java.windf.expand;

import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class ManageAble implements ExpandItem<Entity> {
    @Override
    public String getName() {
        return "是否需要管理";
    }

    @Override
    public String getCode() {
        return "manageAble";
    }

    @Override
    public Type getType() {
        return LangType.BOOLEAN.getType();
    }

    @Override
    public boolean isRequested() {
        return false;
    }

    @Override
    public Object getDefaultValue(Entity expandSlot) {
        return true;
    }

    @Override
    public Class<Entity> getExpandType() {
        return Entity.class;
    }
}

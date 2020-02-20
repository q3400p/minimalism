package com.windf.minimalism.generation.template.java.expand;

import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class RepositoryAble implements ExpandItem<Entity> {

    @Override
    public String getName() {
        return "是否需要对应数据库操作";
    }

    @Override
    public String getCode() {
        return "repositoryAble";
    }

    @Override
    public Type getType() {
        return LangType.Boolean.getType();
    }

    @Override
    public boolean isRequested() {
        return false;
    }

    @Override
    public Object getDefaultValue(Entity entity) {
        return true;
    }

    @Override
    public Class<Entity> getExpandType() {
        return Entity.class;
    }
}

package com.windf.minimalism.generation.template.java.expand;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class TableName implements ExpandItem<Entity> {
    @Override
    public String getName() {
        return "数据库表名";
    }

    @Override
    public String getCode() {
        return "tableName";
    }

    @Override
    public Type getType() {
        return LangType.STRING.getType();
    }

    @Override
    public boolean isRequested() {
        return false;
    }

    @Override
    public Object getDefaultValue(Entity entity) {
        return StringUtil.splitCamelCase(entity.getCode(), "_");
    }

    @Override
    public Class<Entity> getExpandType() {
        return Entity.class;
    }
}

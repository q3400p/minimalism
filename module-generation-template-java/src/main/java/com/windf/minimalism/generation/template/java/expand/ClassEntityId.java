package com.windf.minimalism.generation.template.java.expand;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class ClassEntityId implements ExpandItem<Entity> {
    @Override
    public String getName() {
        return "实体类的全名";
    }

    @Override
    public String getCode() {
        return "classEntityId";
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
    public Object getDefaultValue(Entity expandSlot) {
        String classCode = StringUtil.firstLetterUppercase(expandSlot.getCode());
        return "[package||" + expandSlot.getModule().getId() + ".entity." + classCode + "||" + classCode + "]";
    }

    @Override
    public Class<Entity> getExpandType() {
        return Entity.class;
    }
}

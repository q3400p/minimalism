package com.windf.minimalism.generation.template.java.expand;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Parameter;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class ClassTypeIdOfMethodParameter implements ExpandItem<Parameter> {
    @Override
    public String getName() {
        return "类型的类全名";
    }

    @Override
    public String getCode() {
        return "classTypeId";
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
    public Object getDefaultValue(Parameter expandSlot) {
        String classCode = StringUtil.firstLetterUppercase(expandSlot.getCode());
        return "[package||" + expandSlot.getType().getId() + "||" + classCode + "]";
    }

    @Override
    public Class<Parameter> getExpandType() {
        return Parameter.class;
    }
}
package com.windf.minimalism.generation.template.java.expand;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Parameter;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class ClassTypeIdOfMethodReturn implements ExpandItem<Parameter> {
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
        String typeId = expandSlot.getType().getId();
        String typeNamespace = typeId.substring(0, typeId.lastIndexOf("."));
        String typeCode = StringUtil.firstLetterUppercase(typeId.substring(typeId.lastIndexOf(".") + 1));
        String importTypeId = typeNamespace + "." + typeCode;

        return "[package||" + importTypeId + "||" + typeCode + "]";
    }

    @Override
    public Class<Parameter> getExpandType() {
        return Parameter.class;
    }
}

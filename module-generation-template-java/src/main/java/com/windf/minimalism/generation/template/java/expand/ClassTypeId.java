package com.windf.minimalism.generation.template.java.expand;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;
import com.windf.minimalism.generation.template.java.util.ClassUtil;

public class ClassTypeId implements ExpandItem<Type> {
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
    public Object getDefaultValue(Type expandSlot) {
        String importClassId;
        String classCode = StringUtil.firstLetterUppercase(expandSlot.getCode());
        if (expandSlot instanceof Entity) {
            Entity entity = ((Entity) expandSlot);
            Module module = entity.getModule();
            importClassId = module.getNamespace() + ".module." + module.getCode() + ".entity." + classCode;
        } else {
            importClassId = ClassUtil.getImportClassId(expandSlot.getId());
        }

        return "[package||" + importClassId + "||" + classCode + "]";
    }

    @Override
    public Class<Type> getExpandType() {
        return Type.class;
    }
}

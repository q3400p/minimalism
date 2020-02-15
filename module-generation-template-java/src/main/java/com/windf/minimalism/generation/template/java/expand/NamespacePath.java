package com.windf.minimalism.generation.template.java.expand;

import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class NamespacePath implements ExpandItem<Module> {

    @Override
    public String getName() {
        return "namespace路径";
    }

    @Override
    public String getCode() {
        return "namespacePath";
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
    public Object getDefaultValue(Module module) {
        return module.getNamespace().replace(".", "/");
    }

    @Override
    public Class<Module> getExpandType() {
        return Module.class;
    }
}

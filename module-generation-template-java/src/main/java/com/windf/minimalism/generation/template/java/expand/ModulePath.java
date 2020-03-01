package com.windf.minimalism.generation.template.java.expand;

import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class ModulePath implements ExpandItem<Module> {
    @Override
    public String getName() {
        return "模块的路径";
    }

    @Override
    public String getCode() {
        return "modulePath";
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
    public Object getDefaultValue(Module module) {
        return "/" + module.getCode();
    }

    @Override
    public Class<Module> getExpandType() {
        return Module.class;
    }
}

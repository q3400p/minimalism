package com.windf.minimalism.generation.template.java.expand;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Method;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class APIMethodPath implements ExpandItem<Method> {
    @Override
    public String getName() {
        return "api方法的路径名称";
    }

    @Override
    public String getCode() {
        return "apiFieldPath";
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
    public Object getDefaultValue(Method method) {
        return "/" + StringUtil.splitCamelCase(method.getCode(), "/");
    }

    @Override
    public Class<Method> getExpandType() {
        return Method.class;
    }
}

package com.windf.minimalism.generation.template.java.expand;

import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Method;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class WebMethodAble implements ExpandItem<Method> {
    @Override
    public String getName() {
        return "是否需要api方法";
    }

    @Override
    public String getCode() {
        return "webMethodAble";
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
    public Object getDefaultValue(Method method) {
        return true;
    }

    @Override
    public Class<Method> getExpandType() {
        return Method.class;
    }
}

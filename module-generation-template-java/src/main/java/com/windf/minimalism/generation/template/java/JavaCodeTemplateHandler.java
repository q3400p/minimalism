package com.windf.minimalism.generation.template.java;

import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.model.expand.ExpandItem;
import com.windf.minimalism.generation.model.template.CodeTemplateHandler;
import com.windf.minimalism.generation.template.java.expand.ClassCode;

import java.util.ArrayList;
import java.util.List;

/**
 * java的代码生成的处理程序
 */
public abstract class JavaCodeTemplateHandler implements CodeTemplateHandler {
    @Override
    public List<ExpandItem> getExpandItems() {
        List<ExpandItem> result = new ArrayList<>();
        result.add(new ClassCode());
        return result;
    }

    public Module processModule(Module module) {
        return module;
    }

}

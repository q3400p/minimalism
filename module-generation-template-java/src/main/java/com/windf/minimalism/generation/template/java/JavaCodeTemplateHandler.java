package com.windf.minimalism.generation.template.java;

import com.windf.core.util.BeanUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.model.template.CodeTemplateHandler;
import com.windf.minimalism.generation.template.java.entity.JavaMinEntity;
import com.windf.minimalism.generation.template.java.entity.JavaMinField;

import java.util.ArrayList;
import java.util.List;

/**
 * java的代码生成的处理程序
 */
public abstract class JavaCodeTemplateHandler implements CodeTemplateHandler {

    @Override
    public Module processModule(Module module) {
        return module;
    }

    /**
     * 处理实体
     * @param entity
     */
    @Override
    public Entity processEntity(Entity entity) {
        JavaMinEntity javaMinEntity = new JavaMinEntity();
        BeanUtil.copyProperties(javaMinEntity, entity);
        // TODO 需要改成自己的实体
        List<Field> fields = new ArrayList<>();
        for (Field field : entity.getFields()) {
            JavaMinField javaMinField = new JavaMinField();
            BeanUtil.copyProperties(javaMinField, field);
            fields.add(javaMinField);
        }
        javaMinEntity.setFields(fields);
        return javaMinEntity;
    }
}

package com.windf.minimalism.generation.model;

import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Module;

/**
 * 代码生成的模板，用于定于代码生成的模板路径，以及自定义处理数据等
 */
public interface CodeTemplateHandler {

    /**
     * 返回模板的路径
     * @return
     */
    String getTemplatePath();

    /**
     * 处理模块
     * @param module
     * @return
     */
    Module processModule(Module module);
    /**
     * 处理实体
     * @param entity
     * @return
     */
    Entity processEntity(Entity entity);
}

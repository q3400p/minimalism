package com.windf.minimalism.generation.service;

import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Module;

public interface DataModelHandler {
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

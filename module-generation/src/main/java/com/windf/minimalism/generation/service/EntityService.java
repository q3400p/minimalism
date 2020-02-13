package com.windf.minimalism.generation.service;

import com.windf.core.service.ManageService;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;
import com.windf.minimalism.generation.model.expand.ExpandItemManager;
import com.windf.minimalism.generation.model.expand.ExpandSlot;

import java.util.List;

/**
 * 实体的列表
 */
public interface EntityService extends ManageService<Entity>, ExpandItemManager {
    /**
     * 获取所有实体的类型，可以作为变量类型
     */
    List<Type> listAllEntityType();

    /**
     * 获取模块下的所有实体
     * @param moduleId
     * @return
     */
    List<Entity> getByModuleId(String moduleId);

}

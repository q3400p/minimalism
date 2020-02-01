package com.windf.minimalism.generation.repository;

import com.windf.core.repository.ManageRepository;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Type;

import java.util.List;

public interface EntityRepository extends ManageRepository<Entity> {
    /**
     * 获取所有实体类型
     * @return
     */
    List<Type> listAllEntityType();

    /**
     * 根据模块id获取模块下的所有实体
     * @param moduleId
     * @return
     */
    List<Entity> getByModuleId(String moduleId);
}

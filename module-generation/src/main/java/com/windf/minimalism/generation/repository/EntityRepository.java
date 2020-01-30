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
}

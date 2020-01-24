package com.windf.minimalism.generation.service.business;

import com.windf.core.repository.ManageRepository;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.repository.EntityRepository;
import com.windf.minimalism.generation.service.EntityService;
import com.windf.plugin.service.business.BaseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityServiceImpl extends BaseManageService<Entity> implements EntityService {

    @Autowired
    private EntityRepository entityRepository;

    @Override
    public ManageRepository<Entity> getManageRepository() {
        return entityRepository;
    }

    @Override
    public void create(Entity entity) {
        String moduleId = entity.getModule().getId();

        // 设置id
        entity.setId(moduleId + Entity.ID_POINT + entity.getCode());

        super.create(entity);
    }
}

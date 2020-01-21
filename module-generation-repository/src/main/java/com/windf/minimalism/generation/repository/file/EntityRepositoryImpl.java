package com.windf.minimalism.generation.repository.file;

import com.windf.core.entity.Page;
import com.windf.core.entity.SearchData;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.repository.EntityRepository;
import com.windf.minimalism.generation.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntityRepositoryImpl implements EntityRepository {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public void create(Entity entity) {

    }

    @Override
    public void update(Entity entity) {

    }

    @Override
    public void delete(List<String> ids) {

    }

    @Override
    public Entity detail(String id) {
        return null;
    }

    @Override
    public Page<Entity> search(SearchData searchData) {
        return null;
    }
}

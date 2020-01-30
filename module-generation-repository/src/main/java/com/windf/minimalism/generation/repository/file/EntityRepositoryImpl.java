package com.windf.minimalism.generation.repository.file;

import com.windf.core.entity.Page;
import com.windf.core.entity.SearchData;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.repository.EntityRepository;
import com.windf.minimalism.generation.repository.ModuleRepository;
import com.windf.minimalism.generation.repository.file.entity.Modules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntityRepositoryImpl implements EntityRepository {

    @Override
    public void create(Entity entity) {
        Modules.getInstance().saveEntity(entity);
    }

    @Override
    public void update(Entity entity) {
        this.create(entity);
    }

    @Override
    public void delete(List<String> ids) {
        Modules.getInstance().deleteEntity(ids);
    }

    @Override
    public Entity detail(String id) {
        return Modules.getInstance().getEntity(id);
    }

    @Override
    public Page<Entity> search(SearchData searchData) {
        return null;
    }

    @Override
    public List<Type> listAllEntityType() {
            return Modules.getInstance().listAllEntity();
    }
}

package com.windf.minimalism.generation.repository.file;

import com.windf.core.entity.Page;
import com.windf.core.entity.SearchData;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Method;
import com.windf.minimalism.generation.repository.EntityRepository;
import com.windf.minimalism.generation.repository.MethodRepository;
import com.windf.minimalism.generation.repository.file.entity.Modules;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MethodRepositoryImpl implements MethodRepository {

    @Override
    public void create(Method method) {
        Modules.getInstance().saveMethod(method);

    }

    @Override
    public void update(Method method) {
        this.create(method);
    }

    @Override
    public void delete(List<String> ids) {
        Modules.getInstance().deleteMethod(ids);
    }

    @Override
    public Method detail(String id) {
        return Modules.getInstance().getMethod(id);
    }

    @Override
    public Page<Method> search(SearchData searchData) {
        return null;
    }
}

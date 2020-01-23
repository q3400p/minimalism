package com.windf.minimalism.generation.repository.file;

import com.windf.core.entity.Page;
import com.windf.core.entity.SearchData;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.repository.FieldRepository;
import com.windf.minimalism.generation.repository.file.entity.Modules;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FieldRepositoryImpl implements FieldRepository {
    @Override
    public void create(Field field) {
        Modules.getInstance().saveField(field);
    }

    @Override
    public void update(Field field) {
        this.create(field);
    }

    @Override
    public void delete(List<String> ids) {
        Modules.getInstance().deleteField(ids);
    }

    @Override
    public Field detail(String id) {
        return Modules.getInstance().getField(id);
    }

    @Override
    public Page<Field> search(SearchData searchData) {
        return null;
    }
}

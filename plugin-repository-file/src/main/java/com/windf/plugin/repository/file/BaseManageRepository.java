package com.windf.plugin.repository.file;

import com.windf.core.entity.BaseEntity;
import com.windf.core.entity.Page;
import com.windf.core.entity.SearchData;
import com.windf.core.repository.ManageRepository;

import java.util.List;

public class BaseManageRepository<T extends BaseEntity> implements ManageRepository<T> {

    @Override
    public void create(T entity) {
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(List<String> ids) {

    }

    @Override
    public T detail(String id) {
        return null;
    }

    @Override
    public Page<T> search(SearchData searchData) {
        return null;
    }
}

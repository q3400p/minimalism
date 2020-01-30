package com.windf.minimalism.generation.service.business;

import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.entity.type.LangType;
import com.windf.minimalism.generation.service.EntityService;
import com.windf.minimalism.generation.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private EntityService entityService;

    private Map<String, Type> typeMap;

    @Override
    public List<Type> listAll() {
        return new ArrayList<>(typeMap.values());
    }

    @Override
    public Type detail(String id) {
        Map<String, Type> typeMap = listAllBaseType();

        return typeMap.get(id);
    }

    @Override
    public void addEntity(Entity entity) {
        Map<String, Type> typeMap = this.listAllBaseType();
        typeMap.put(entity.getId(), entity);
    }

    private Map<String, Type> listAllBaseType() {
        if (typeMap != null) {
            return typeMap;
        }

        typeMap = new HashMap<>();

        // 基础类型
        LangType[] langTypes = LangType.class.getEnumConstants();
        for (LangType langType : langTypes) {
            typeMap.put(langType.getCode(), langType.getType());
        }

        // 实体类型
        List<Type> entityTypes = entityService.listAllEntityType();
        for (Type type : entityTypes) {
            typeMap.put(type.getTypeCode(), type);
        }

        return typeMap;
    }
}

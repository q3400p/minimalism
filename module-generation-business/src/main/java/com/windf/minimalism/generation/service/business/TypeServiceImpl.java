package com.windf.minimalism.generation.service.business;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.service.EntityService;
import com.windf.minimalism.generation.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private EntityService entityService;

    /**
     * 保存所有的类型和map映射关系
     */
    private Map<String, Type> typeMap;

    @Override
    public List<Type> listAll(String key) {
        // 获取所有类型
        List<Type> types = new ArrayList<>(this.listAllBaseType().values());

        // 如果没有搜索关键字，直接返回
        if (StringUtil.isEmpty(key)) {
            return types;
        }

        // 根据关键字进行筛选
        List<Type> result = new ArrayList<>();
        for (Type type : types) {
            // code筛选
            if (type.getCode().indexOf(key) > -1) {
                result.add(type);
                break;
            }

            // name筛选
            if (type.getName().indexOf(key) > -1) {
                result.add(type);
                break;
            }
        }
        return result;
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
            typeMap.put(type.getId(), type);
        }

        return typeMap;
    }
}

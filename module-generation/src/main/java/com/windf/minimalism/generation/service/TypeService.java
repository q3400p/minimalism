package com.windf.minimalism.generation.service;

import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Type;

import java.util.List;

public interface TypeService {
    /**
     * 查询所有类型
     * 根据关键字搜索
     * @param key
     * @return
     */
    List<Type> listAll(String key);

    /**
     * 单个类型的详情
     * @param id
     * @return
     */
    Type detail(String id);

    /**
     * 将实体添加到类型列表中
     * 如果没有添加，如果有的话更新
     * 更新操作是被动的，因为类型这里和实体引用的是一个对象
     * @param entity
     */
    void addEntity(Entity entity);
}

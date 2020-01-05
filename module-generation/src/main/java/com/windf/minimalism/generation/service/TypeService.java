package com.windf.minimalism.generation.service;

import com.windf.minimalism.generation.entity.Type;

import java.util.List;

public interface TypeService {
    /**
     * 查询所有类型
     * @return
     */
    List<Type> listAll();

    /**
     * 单个类型的详情
     * @param id
     * @return
     */
    Type detail(String id);
}

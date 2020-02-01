package com.windf.minimalism.generation.service;

import com.windf.core.service.ManageService;
import com.windf.minimalism.generation.entity.Module;

public interface ModuleService extends ManageService<Module> {
    /**
     * 模块提交，生成模块对应的内容
     * @param moduleId
     */
    void commit(String moduleId);

}

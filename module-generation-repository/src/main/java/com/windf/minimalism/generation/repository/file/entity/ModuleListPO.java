package com.windf.minimalism.generation.repository.file.entity;

import com.windf.core.entity.BaseEntity;
import com.windf.minimalism.generation.entity.Module;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于查看所有的Module
 */
public class ModuleListPO extends BaseEntity {
    /**
     * 所有的模块基本信息
     */
    private List<Module> modules = new ArrayList<>();

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}

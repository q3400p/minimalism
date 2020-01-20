package com.windf.minimalism.generation.repository.file.entity;

import com.windf.minimalism.generation.entity.*;

import java.util.List;

/**
 * 用于存储的module对象
 */
public class ModulePO extends Module {
    /**
     * 模块的所有实体
     */
    private List<Entity> entities;
    /**
     * 模块的所有配置
     */
    private List<Configuration> configurations;
    /**
     * 模块的所有常量
     */
    private List<Constant> constants;
    /**
     * 发出的事件列表
     */
    private List<Event> events;
    /**
     * 模块的所属依赖
     */
    private List<Depend> depends;

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }

    public List<Constant> getConstants() {
        return constants;
    }

    public void setConstants(List<Constant> constants) {
        this.constants = constants;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Depend> getDepends() {
        return depends;
    }

    public void setDepends(List<Depend> depends) {
        this.depends = depends;
    }
}

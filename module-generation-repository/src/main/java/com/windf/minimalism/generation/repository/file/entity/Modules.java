package com.windf.minimalism.generation.repository.file.entity;

import com.windf.core.exception.UserException;
import com.windf.core.util.BeanUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.entity.Parameter;
import com.windf.minimalism.generation.repository.file.config.ModuleConfig;
import com.windf.plugin.repository.file.BaseJSONFileRepository;

import java.util.*;

public class Modules extends BaseJSONFileRepository {
    private static Modules modules = new Modules();

    public static Modules getInstance() {
        return modules;
    }

    /**
     * 模块List的PO，用于保存模块
     */
    private ModuleListPO moduleListPO;
    /**
     * 存放模块信息的对应关系
     */
    private Map<String, ModulePO> modulePOMap = new HashMap<>();

    private Modules() {
        // 读取json文件，获取模块列表
        this.readModuleList();
    }

    /**
     * 保存模块
     * @param modulePO
     */
    public void saveModule(ModulePO modulePO) {
        // 获取模块路径
        String modulePath = this.getModuleSavePath(modulePO.getId());

        // 保存文件
        this.write(modulePath, modulePO);

        // 写入缓存
        modulePOMap.put(modulePO.getId(), modulePO);

        // 保存模块列表
        this.updateModuleList(modulePO);
    }

    /**
     * 删除模块
     * @param ids
     */
    public void deleteModule(List<String> ids) {
        for (String id : ids) {
            // 读取文件
            String filePath = this.getModuleSavePath(id);

            // 删除文件
            boolean success = this.delete(filePath);

            // 删除缓存
            modulePOMap.remove(id);

            // 维护模块列表
            this.removeModuleFromList(id);

            // 如果文件不存在，抛出异常
            if (!success) {
                throw new UserException("模块不存在");
            }
        }
    }

    /**
     * 根据id获取模块
     * @param id
     */
    public ModulePO getModule(String id) {
        // 从map缓存总获取
        ModulePO modulePO = modulePOMap.get(id);
        if (modulePO != null) {
            return modulePO;
        }

        // 读取文件路径
        String filePath = this.getModuleSavePath(id);

        // 读取json文件
        modulePO = this.read(filePath, ModulePO.class);

        // 存储模块
        modulePOMap.put(id, modulePO);

        return modulePO;
    }

    /**
     * 获取所有模块
     * @return
     */
    public List<Module> listAllModules() {
        return moduleListPO.getModules();
    }

    // 在指定模块中添加实体
    public void saveEntity(Entity entity) {
        // 获取实体的模块
        Module module = entity.getModule();

        // 设置实体的module为空，不进行重复保存
        entity.setModule(null);

        // 获取模块
        ModulePO modulePO = this.getModule(module.getId());
        if (modulePO == null) {
            throw new UserException("模块找不到");
        }

        // 获取该模块的实体列表
        List<Entity> entities = modulePO.getEntities();
        if (entities == null) {
            entities = new ArrayList<>();
            modulePO.setEntities(entities);
        }

        // 找到模块
        boolean hasEntity = false;
        for (Entity e : entities) {
            // 如果找到对应的实体，进行修改
            if (e.getId().equals(entity.getId())) {
                // 复制内容
                BeanUtil.copyProperties(e, entity);
                // 已经找到模块，后面就不用添加了
                hasEntity = true;
                break;
            }
        }

        // 如果没有找到模块，添加新的实体
        if (!hasEntity) {
            entities.add(entity);
        }

        // 保存模块
        this.saveModule(modulePO);
    }

    /**
     * 删除实体
     * @param moduleId
     * @param ids
     */
    public void deleteEntity(String moduleId, List<String> ids) {
        ModulePO modulePO = this.getModule(moduleId);
        if (modulePO == null) {
            throw new UserException("模块不存在");
        }

        // 遍历模块中的实体，进行删除
        List<Entity> entities = modulePO.getEntities();
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            // 遍历ids，找到要删除的id和entity
            Iterator<String> idIterator = ids.iterator();
            while (idIterator.hasNext()) {
                String deleteId = idIterator.next();
                // 匹配要删除的entity和id
                if (deleteId.equals(entity.getId())) {
                    // 删除要删除的entity
                    iterator.remove();
                    // 要删除的id中去除
                    idIterator.remove();
                    break;
                }
            }

            // 如果没有要移除的id，结束遍历
            if (ids.size() == 0) {
                break;
            }
        }
    }

    /**
     * 读取文件列表
     */
    private void readModuleList() {
        // 读取列表文件路径
        String filePath = this.getModuleListFile();
        // 读取文件内容
        moduleListPO = this.read(filePath, ModuleListPO.class);
        // 如果获取不到，新创建一个
        if (moduleListPO == null) {
            moduleListPO = new ModuleListPO();
        }
    }

    /**
     * 修改文件列表中的模块，并且持久化到文件中
     * @param module
     */
    private void updateModuleList(ModulePO module) {
        // 先读取模块列表
        this.readModuleList();

        List<Module> modules = moduleListPO.getModules();
        boolean moduleUnSaved = true;
        for (Module dbModule : modules) {
            if (dbModule.getId().equals(module.getId())) {
                // 复制bean的树形到db中，这里的db是文件系统
                BeanUtil.copyProperties(dbModule, module);
                // 标记已经找到模块
                moduleUnSaved = false;
                break;
            }
        }

        // 如果模块没有保存，添加到list中
        if (moduleUnSaved) {
            modules.add(module);
        }

        this.saveModuleList();
    }

    /**
     * 存储模块列表
     */
    private void saveModuleList() {
        // 读取列表文件路径
        String filePath = this.getModuleListFile();
        // 保存文件
        this.write(filePath, moduleListPO);
    }

    /**
     * 从模块列表中，删除指定id的模块，并且进行持久化
     * @param id
     */
    private void removeModuleFromList(String id) {
        Iterator<Module> iterator = moduleListPO.getModules().iterator();
        while (iterator.hasNext()) {
            Module module = iterator.next();
            if (module.getId().equals(id)) {
                iterator.remove();
                break;
            }
        }

        // 模块列表持久化
        this.saveModuleList();
    }

    /**
     * 获取模块的保存路径
     * @param moduleId
     * @return
     */
    protected String getModuleSavePath(String moduleId) {
        return ModuleConfig.getInstance().getModulePath() + "/" +
                moduleId + "." + ModuleConfig.getInstance().getFileSuffix();
    }

    /**
     * 获取模块列表
     * @return
     */
    protected String getModuleListFile() {
        return ModuleConfig.getInstance().getModulePath() + "/" +
                ModuleConfig.getInstance().getModuleListFileName();
    }
}

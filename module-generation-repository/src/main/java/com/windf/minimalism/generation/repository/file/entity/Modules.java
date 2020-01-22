package com.windf.minimalism.generation.repository.file.entity;

import com.windf.core.exception.UserException;
import com.windf.core.util.BeanUtil;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.repository.file.config.ModuleConfig;
import com.windf.plugin.repository.file.BaseJSONFileRepository;

import java.util.Iterator;
import java.util.List;

public class Modules extends BaseJSONFileRepository {
    private static Modules modules = new Modules();

    public static Modules getInstance() {
        return modules;
    }

    /**
     * 模块List的PO，用于保存模块
     */
    private ModuleListPO moduleListPO;

    private Modules() {
        // 读取json文件
        this.readModuleList();
    }

    /**
     * 保存模块
     * @param module
     */
    public void saveModule(Module module) {
        // 获取模块路径
        String modulePath = this.getModuleSavePath(module.getId());

        // 保存文件
        this.write(modulePath, module);

        // 保存模块列表
        this.updateModuleList(module);
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
    public Module getModule(String id) {
        // 读取文件路径
        String filePath = this.getModuleSavePath(id);

        // 读取json文件
        return this.read(filePath, Module.class);
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
    private void updateModuleList(Module module) {
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

    public List<Module> listAllModules() {
        return moduleListPO.getModules();
    }
}

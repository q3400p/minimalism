package com.windf.minimalism.generation.repository.file;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.windf.core.entity.Page;
import com.windf.core.entity.SearchData;
import com.windf.core.exception.UserException;
import com.windf.core.util.BeanUtil;
import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.repository.ModuleRepository;
import com.windf.minimalism.generation.repository.file.config.ModuleConfig;
import com.windf.minimalism.generation.repository.file.entity.ModuleListPO;
import com.windf.plugin.repository.file.BaseManageRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ModuleRepositoryImpl extends BaseManageRepository<Module> implements ModuleRepository {

    /**
     * 模块List的PO，用于保存模块
     */
    private ModuleListPO moduleListPO;

    @Override
    public void create(Module module) {
        // 获取模块路径
        String modulePath = this.getModuleSavePath(module.getId());

        // 保存文件
        this.saveJsonFile(modulePath, module);

        // 保存模块列表
        this.updateModuleList(module);
    }

    @Override
    public void update(Module module) {
        this.create(module);
    }

    @Override
    public void delete(List<String> ids) {
        for (String id : ids) {
            // 读取文件
            String filePath = this.getModuleSavePath(id);

            // 删除文件
            boolean success = this.deleteFile(filePath);

            // 维护模块列表
            this.removeModuleFromList(id);

            // 如果文件不存在，抛出异常
            if (!success) {
                throw new UserException("模块不存在");
            }
        }
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

    @Override
    public Module detail(String id) {
        // 读取文件路径
        String filePath = this.getModuleSavePath(id);

        // 读取json文件
        return this.readObjectByJSONFile(filePath, Module.class);
    }

    @Override
    public Page<Module> search(SearchData searchData) {
        // 读取json文件
        this.readModuleList();

        // 进行搜索
        List<Module> result = new ArrayList<>();
        for (Module module : moduleListPO.getModules()) {
            if (searchData != null) {
                // 名称搜索
                String name = (String) searchData.getCondition().get("name");
                if (StringUtil.isNotEmpty(name) && module.getName().indexOf(name) == -1) {
                    continue;
                }

                // id搜索
                String id = (String) searchData.getCondition().get("id");
                if (StringUtil.isNotEmpty(id) && module.getId().indexOf(id) == -1) {
                    continue;
                }

                // namespace搜索
                String namespace = (String) searchData.getCondition().get("namespace");
                if (StringUtil.isNotEmpty(namespace) && module.getNamespace().indexOf(namespace) == -1) {
                    continue;
                }
            }

            result.add(module);
        }

        // 构建page返回对象
        Page page = new Page(1L, -1);
        page.setTotal(new Long(result.size()));
        page.setData(result);
        return page;
    }

    /**
     * 读取文件列表
     */
    private void readModuleList() {
        // 读取列表文件路径
        String filePath = this.getModuleListFile();
        // 读取文件内容
        moduleListPO = this.readObjectByJSONFile(filePath, ModuleListPO.class);
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
        this.saveJsonFile(filePath, moduleListPO);
    }

    public String getModuleSavePath(String moduleId) {
        return ModuleConfig.getInstance().getModulePath() + "/" +
                moduleId + "." + ModuleConfig.getInstance().getFileSuffix();
    }

    public String getModuleListFile() {
        return ModuleConfig.getInstance().getModulePath() + "/" +
                ModuleConfig.getInstance().getModuleListFileName();
    }
}

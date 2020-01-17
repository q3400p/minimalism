package com.windf.minimalism.generation.repository.file;

import com.windf.core.entity.Page;
import com.windf.core.entity.SearchData;
import com.windf.core.util.FileUtil;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.repository.ModuleRepository;
import com.windf.minimalism.generation.repository.file.config.ModuleConfig;
import com.windf.plugin.repository.file.BaseManageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModuleRepositoryImpl extends BaseManageRepository<Module> implements ModuleRepository {

    @Override
    public void create(Module module) {
        // 获取模块路径
        String modulePath = this.getModuleSavePath(module.getId());

        // 保存文件
        this.saveJsonFile(modulePath, module);
    }

    @Override
    public void update(Module module) {
        this.create(module);
    }

    @Override
    public void delete(List<String> ids) {

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
        return null;
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

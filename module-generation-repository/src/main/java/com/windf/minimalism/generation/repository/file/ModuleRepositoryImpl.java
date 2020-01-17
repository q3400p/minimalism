package com.windf.minimalism.generation.repository.file;

import com.windf.core.entity.Page;
import com.windf.core.entity.SearchData;
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
        String modulePath = this.getModuleSavePath(module);

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
        return null;
    }

    @Override
    public Page<Module> search(SearchData searchData) {
        return null;
    }

    public String getModuleSavePath(Module module) {
        return ModuleConfig.getInstance().getModulePath() + "/" +
                module.getId() + "." + ModuleConfig.getInstance().getFileSuffix();
    }

    public String getModuleListFile(Module module) {
        return ModuleConfig.getInstance().getModulePath() + "/" +
                ModuleConfig.getInstance().getModuleListFileName();
    }
}

package com.windf.minimalism.generation.repository.file;

import com.windf.core.entity.Page;
import com.windf.core.entity.SearchData;
import com.windf.core.util.BeanUtil;
import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.repository.ModuleRepository;
import com.windf.minimalism.generation.repository.file.entity.ModulePO;
import com.windf.minimalism.generation.repository.file.entity.Modules;
import com.windf.plugin.repository.file.BaseJSONFileManageRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ModuleRepositoryImpl extends BaseJSONFileManageRepository<Module> implements ModuleRepository {

    @Override
    public void create(Module module) {
        Modules modules = Modules.getInstance();

        // 复制bean的属性
        ModulePO modulePO = modules.getModule(module.getId());
        if (modulePO == null) {
            modulePO = new ModulePO();
        }
        BeanUtil.copyProperties(modulePO, module);

        modules.saveModule(modulePO);
    }

    @Override
    public void update(Module module) {
        this.create(module);
    }

    @Override
    public void delete(List<String> ids) {
        Modules.getInstance().deleteModule(ids);
    }

    @Override
    public Module detail(String id) {
        Module sourceModule = Modules.getInstance().getModule(id);

        // 如果找不到模块，返回null
        if (sourceModule == null) {
            return null;
        }

        // 复制模块信息
        Module module = new Module();
        BeanUtil.copyProperties(module, sourceModule);

        return module;
    }

    @Override
    public Page<Module> search(SearchData searchData) {
        // 获得所有模块
        List<Module> moduleList = Modules.getInstance().listAllModules();

        // 进行搜索
        List<Module> result = new ArrayList<>();
        for (Module module : moduleList) {
            if (searchData != null) {
                // 名称搜索
                String name = (String) searchData.getCondition().get("name");
                if (StringUtil.isNotEmpty(name) && !module.getName().contains(name)) {
                    continue;
                }

                // id搜索
                String id = (String) searchData.getCondition().get("id");
                if (StringUtil.isNotEmpty(id) && !module.getId().contains(id)) {
                    continue;
                }

                // namespace搜索
                String namespace = (String) searchData.getCondition().get("namespace");
                if (StringUtil.isNotEmpty(namespace) && !module.getNamespace().contains(namespace)) {
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

}

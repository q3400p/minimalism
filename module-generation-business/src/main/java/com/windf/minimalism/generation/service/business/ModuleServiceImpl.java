package com.windf.minimalism.generation.service.business;

import com.windf.core.repository.ManageRepository;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.repository.ModuleRepository;
import com.windf.minimalism.generation.service.ModuleService;
import com.windf.plugin.service.business.BaseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl extends BaseManageService<Module> implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public ManageRepository<Module> getManageRepository() {
        return moduleRepository;
    }
}

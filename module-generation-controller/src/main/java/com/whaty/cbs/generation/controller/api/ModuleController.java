package com.whaty.cbs.generation.controller.api;

import com.whaty.cbs.plugins.web.controller.ManageController;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModuleController extends ManageController<Module> {

    @Autowired
    private ModuleService moduleService;

    @Override
    public ModuleService getManageService() {
        return this.moduleService;
    }
}

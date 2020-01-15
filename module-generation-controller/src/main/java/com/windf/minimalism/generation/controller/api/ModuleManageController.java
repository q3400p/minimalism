package com.windf.minimalism.generation.controller.api;

import com.windf.core.entity.ResultData;
import com.windf.plugin.controller.api.controller.BaseManageController;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/module")
public class ModuleManageController extends BaseManageController<Module> {

    @Autowired
    private ModuleService moduleService;

    @Override
    public ModuleService getManageService() {
        return this.moduleService;
    }

    @RequestMapping("/test")
    public ResultData test() {
        return response().success("test222");
    }
}

package com.windf.minimalism.generation.controller.api;

import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.service.EntityService;
import com.windf.minimalism.generation.service.ModuleService;
import com.windf.plugin.controller.api.controller.BaseManageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entity") // TODO 修改到变量中
public class EntityManageController extends BaseManageController<Entity> {

    @Autowired
    private EntityService entityService;

    @Override
    public EntityService getManageService() {
        return this.entityService;
    }
}

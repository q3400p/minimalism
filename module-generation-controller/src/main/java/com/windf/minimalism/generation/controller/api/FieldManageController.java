package com.windf.minimalism.generation.controller.api;

import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.service.FieldService;
import com.windf.minimalism.generation.service.ModuleService;
import com.windf.plugin.controller.api.controller.BaseManageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/field")
public class FieldManageController extends BaseManageController<Field> {

    @Autowired
    private FieldService fieldService;

    @Override
    public FieldService getManageService() {
        return this.fieldService;
    }
}

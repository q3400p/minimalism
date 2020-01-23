package com.windf.minimalism.generation.controller.api;

import com.windf.minimalism.generation.entity.Method;
import com.windf.minimalism.generation.service.MethodService;
import com.windf.plugin.controller.api.controller.BaseManageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/method")
public class MethodManageController extends BaseManageController<Method> {

    @Autowired
    private MethodService methodService;

    @Override
    public MethodService getManageService() {
        return this.methodService;
    }
}

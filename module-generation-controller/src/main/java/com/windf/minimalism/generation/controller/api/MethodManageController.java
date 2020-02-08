package com.windf.minimalism.generation.controller.api;

import com.windf.core.entity.ResultData;
import com.windf.minimalism.generation.entity.Method;
import com.windf.minimalism.generation.model.expand.EntityExpandItem;
import com.windf.minimalism.generation.model.expand.MethodExpandItem;
import com.windf.minimalism.generation.service.MethodService;
import com.windf.plugin.controller.api.controller.BaseManageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/method")
public class MethodManageController extends BaseManageController<Method> {

    @Autowired
    private MethodService methodService;

    @Override
    public MethodService getManageService() {
        return this.methodService;
    }

    @GetMapping("/expand")
    public ResultData getExpandItemList() {
        List<MethodExpandItem> data = methodService.getExpandItemList();

        return response().successData(data);
    }
}

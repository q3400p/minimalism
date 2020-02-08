package com.windf.minimalism.generation.controller.api;

import com.windf.core.entity.ResultData;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.model.expand.EntityExpandItem;
import com.windf.minimalism.generation.model.expand.ExpandItem;
import com.windf.minimalism.generation.service.EntityService;
import com.windf.plugin.controller.api.controller.BaseManageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entity") // TODO 修改到变量中
public class EntityManageController extends BaseManageController<Entity> {

    @Autowired
    private EntityService entityService;

    @Override
    public EntityService getManageService() {
        return this.entityService;
    }

    @GetMapping("/expand")
    public ResultData getExpandItemList() {
        List<ExpandItem> data = entityService.getExpandItemList();

        return response().successData(data);
    }
}

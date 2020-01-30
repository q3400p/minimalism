package com.windf.minimalism.generation.controller.api;

import com.windf.core.entity.ResultData;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.service.TypeService;
import com.windf.plugin.controller.api.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController extends BaseController {

    @Autowired
    private TypeService typeService;

    @GetMapping("")
    public ResultData listAll(@RequestParam(required = false) String key) {
        List<Type> data = typeService.listAll(key);

        return response().successData(data);
    }

}

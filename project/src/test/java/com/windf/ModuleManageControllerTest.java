package com.windf;


import com.windf.minimalism.generation.entity.Module;
import com.windf.plugin.controller.api.BaseManageControllerTest;

import java.util.ArrayList;
import java.util.List;

public class ModuleManageControllerTest extends BaseManageControllerTest<Module> {

    @Override
    protected String getBasePath() {
        return "/module";
    }

    @Override
    protected List<Module> getCreateData() {
        List<Module> modules = new ArrayList<>();

        Module data = new Module();
        data.setNamespace("com.windf");
        data.setCode("test");
        data.setName("测试模块");
        data.setDescription("测试用的模块，一会就删除了");
        data.setStatus("1");
        data.setId("com.windf.test");
        modules.add(data);

        data = new Module();
        data.setNamespace("com.windf");
        data.setCode("test2");
        data.setName("测试模块");
        data.setDescription("测试用的模块，一会就删除了");
        data.setStatus("1");
        data.setId("com.windf.test2");
        modules.add(data);

        data = new Module();
        data.setNamespace("com.windf");
        data.setCode("test3");
        data.setName("测试模块");
        data.setDescription("测试用的模块，一会就删除了");
        data.setStatus("1");
        data.setId("com.windf.test3");
        modules.add(data);

        return modules;
    }

    @Override
    protected String getDataId() {
        return "com.windf.test";
    }

    @Override
    protected String getUpdateStatus() {
        return "2";
    }

    @Override
    protected Class<Module> getDataType() {
        return Module.class;
    }

    @Override
    protected void ready() {

    }

    @Override
    protected void destroy() {

    }
}

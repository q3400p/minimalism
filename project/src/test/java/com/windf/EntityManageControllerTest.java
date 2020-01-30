package com.windf;

import com.alibaba.fastjson.JSONArray;
import com.windf.core.entity.ResultData;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Module;
import com.windf.plugin.controller.api.BaseManageControllerTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EntityManageControllerTest extends BaseManageControllerTest<Entity> {

    @Test
    public void t401Search() {

    }

    @Test
    public void t102TypeAdd() {
        // TODO 父类和子类不能一起排序的问题，可能还是要提取service
        ResultData resultData = restTemplate.getForObject("/type/?key={key}", ResultData.class, "com.windf.test.test2");
        JSONArray jsonArray = (JSONArray) resultData.getData();
        // Assert.assertTrue("添加完实体后能查询到数据", jsonArray.size() > 0);
    }

    @Override
    protected String getBasePath() {
        return "/entity";
    }

    @Override
    protected List<Entity> getCreateData() {
        List<Entity> entities = new ArrayList<>();

        Entity data = new Entity();
        data.setId("com.windf.test.test");
        data.setCode("test");
        data.setName("测试实体");
        data.setDescription("测试用的实体，一会就删除了");
        data.setStatus("1");
        Module module = new Module();
        module.setId("com.windf.test");
        data.setModule(module);
        entities.add(data);

        data = new Entity();
        data.setId("com.windf.test.test2");
        data.setCode("test2");
        data.setName("测试模块");
        data.setDescription("测试用的模块，一会就删除了");
        data.setStatus("1");
        module = new Module();
        module.setId("com.windf.test");
        data.setModule(module);
        entities.add(data);

        data = new Entity();
        data.setId("com.windf.test.test3");
        data.setCode("test3");
        data.setName("测试模块");
        data.setDescription("测试用的模块，一会就删除了");
        data.setStatus("1");
        module = new Module();
        module.setId("com.windf.test");
        data.setModule(module);
        entities.add(data);

        return entities;
    }

    @Override
    protected String getDataId() {
        return "com.windf.test.test";
    }

    @Override
    protected String getUpdateStatus() {
        return "2";
    }

    @Override
    protected Class<Entity> getDataType() {
        return Entity.class;
    }

    @Override
    protected void ready() {
        // 创建模块
        Module module = new Module();
        module.setNamespace("com.windf");
        module.setCode("test");
        module.setName("测试模块");
        module.setDescription("测试用的模块，一会就删除了");
        module.setStatus("1");

        restTemplate.postForEntity("/module/", module, ResultData.class);
    }

    @Override
    protected void destroy() {
        // 删除模块
        restTemplate.delete("/module/{id}", "com.windf.test");
    }
}

package com.windf;

import com.windf.core.entity.ResultData;
import com.windf.core.util.CollectionUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Module;
import com.windf.plugin.controller.api.BaseManageControllerTest;
import com.windf.plugin.controller.api.SpringUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class EntityManageControllerTest extends BaseManageControllerTest<Entity> {

    @BeforeClass
    public static void readyModule() {
        // 创建模块
        Module module = new Module();
        module.setNamespace("com.windf");
        module.setCode("test");
        module.setName("测试模块");
        module.setDescription("测试用的模块，一会就删除了");
        module.setStatus("1");

        TestRestTemplate restTemplate = SpringUtil.getBean(TestRestTemplate.class);
        restTemplate.postForEntity("/module/", module, ResultData.class);
    }

    @AfterClass
    public static void destroy() {
        // 删除模块
        TestRestTemplate restTemplate = SpringUtil.getBean(TestRestTemplate.class);
        restTemplate.delete("/module/{id}", "com.windf.test");
    }

    @Test
    public void t401Search() {

    }

    @Override
    protected String getBasePath() {
        return "/entity";
    }

    @Override
    protected List<Entity> getReadyData() {
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
}
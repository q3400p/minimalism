package com.windf;

import com.windf.core.entity.ResultData;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.entity.Method;
import com.windf.minimalism.generation.entity.Module;
import com.windf.plugin.controller.api.BaseManageControllerTest;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

public class FieldManageControllerTest extends BaseManageControllerTest<Field> {

    @Test
    public void t401Search() {
    }

    @Override
    protected String getBasePath() {
        return "/field";
    }

    @Override
    protected List<Field> getCreateData() {
        List<Field> fields = new ArrayList<>();

        Field data = new Field();
        data.setId("com.windf.test.test.testField");
        data.setCode("testField");
        data.setName("测试字段");
        data.setDescription("测试用的字段，一会就删除了");
        data.setStatus("1");
        Entity entity = new Entity();
        entity.setId("com.windf.test.test");
        data.setEntity(entity);
        fields.add(data);

        data = new Field();
        data.setId("com.windf.test.test.testField2");
        data.setCode("testField2");
        data.setName("测试字段");
        data.setDescription("测试用的字段，一会就删除了");
        data.setStatus("1");
        entity = new Entity();
        entity.setId("com.windf.test.test");
        data.setEntity(entity);
        fields.add(data);

        data = new Field();
        data.setId("com.windf.test.test.testField3");
        data.setCode("testField3");
        data.setName("测试字段");
        data.setDescription("测试用的字段，一会就删除了");
        data.setStatus("1");
        entity = new Entity();
        entity.setId("com.windf.test.test");
        data.setEntity(entity);
        fields.add(data);

        return fields;
    }

    @Override
    protected String getDataId() {
        return "com.windf.test.test.testField";
    }

    @Override
    protected String getUpdateStatus() {
        // TODO 考虑重构提取抽象
        return "2";
    }

    @Override
    protected Class<Field> getDataType() {
        return Field.class;
    }

    @Override
    protected void ready() {
        // TODO 需要重构，这些代码会有重复，考虑提取到service中
        // 创建模块
        Module module = new Module();
        module.setId("com.windf.test");
        module.setNamespace("com.windf");
        module.setCode("test");
        module.setName("测试模块");
        module.setDescription("测试用的模块，一会就删除了");
        module.setStatus("1");
        restTemplate.postForEntity("/module/", module, ResultData.class);

        // 创建实体
        Entity entity = new Entity();
        entity.setId("com.windf.test.test");
        entity.setCode("test");
        entity.setName("测试实体");
        entity.setDescription("测试用的实体，一会就删除了");
        entity.setStatus("1");
        module = new Module();
        module.setId("com.windf.test");
        entity.setModule(module);
        restTemplate.postForEntity("/entity/", entity, ResultData.class);
    }

    @Override
    protected void destroy() {
        // 删除模块
        restTemplate.delete("/module/{id}", "com.windf.test");
    }
}

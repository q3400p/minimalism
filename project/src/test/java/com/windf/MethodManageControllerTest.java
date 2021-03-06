package com.windf;

import com.windf.core.entity.ResultData;
import com.windf.minimalism.generation.entity.*;
import com.windf.plugin.controller.api.BaseManageControllerTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MethodManageControllerTest extends BaseManageControllerTest<Method> {

    @Test
    public void t401Search() {

    }

    @Override
    protected String getBasePath() {
        return "/method";
    }

    @Override
    protected List<Method> getCreateData() {
        List<Method> methods = new ArrayList<>();

        Method data = new Method();
        data.setId("com.windf.test.test.testMethod");
        data.setCode("testMethod");
        data.setName("测试方法");
        data.setDescription("测试用的方法，一会就删除了");
        data.setStatus("1");
        Entity entity = new Entity();
        entity.setId("com.windf.test.test");
        data.setEntity(entity);
        // 设置参数
        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter();
        parameter.setType(new Type("Field"));
        parameter.setCode("id");
        parameter.setVerify("0-20");
        parameters.add(parameter);
        parameter = new Parameter();
        parameter.setType(new Type("com.windf.test.test"));
        parameter.setCode("test");
        parameter.setVerify("not null");
        parameters.add(parameter);
        data.setParameters(parameters);
        // 设置返回值
        Return methodReturn = new Return();
        methodReturn.setType(new Type("Field"));
        data.setMethodReturn(methodReturn);
        methods.add(data);

        data = new Method();
        data.setId("com.windf.test.test.testMethod2");
        data.setCode("testMethod2");
        data.setName("测试方法");
        data.setDescription("测试用的方法，一会就删除了");
        data.setStatus("1");
        entity = new Entity();
        entity.setId("com.windf.test.test");
        data.setEntity(entity);
        // 没有参数
        // 设置返回值
        methodReturn = new Return();
        methodReturn.setType(new Type("Field"));
        data.setMethodReturn(methodReturn);
        methods.add(data);

        data = new Method();
        data.setId("com.windf.test.test.testMethod3");
        data.setCode("testMethod3");
        data.setName("测试方法");
        data.setDescription("测试用的方法，一会就删除了");
        data.setStatus("1");
        entity = new Entity();
        entity.setId("com.windf.test.test");
        data.setEntity(entity);
        // 设置参数
        parameters = new ArrayList<>();
        parameter = new Parameter();
        parameter.setType(new Type("Field"));
        parameter.setType(new Type("Field"));
        parameter.setCode("id");
        parameter.setVerify("0-20");
        parameters.add(parameter);
        data.setParameters(parameters);
        // 没返回值
        methods.add(data);

        return methods;
    }

    @Override
    protected String getDataId() {
        return "com.windf.test.test.testMethod";
    }

    @Override
    protected String getUpdateStatus() {
        // TODO 考虑重构提取抽象
        return "2";
    }

    @Override
    protected Class<Method> getDataType() {
        return Method.class;
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

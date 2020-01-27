package com.windf;


import com.alibaba.fastjson.JSONObject;
import com.windf.core.entity.ResultData;
import com.windf.minimalism.generation.entity.Module;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ModuleManageControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private List<Module> datas = new ArrayList<>();

    @Before
    public void readyData() {

        Module data = new Module();
        data.setNamespace("com.windf");
        data.setCode("test");
        data.setName("测试模块");
        data.setDescription("测试用的模块，一会就删除了");

        datas.add(data);

        data = new Module();
        data.setNamespace("com.windf");
        data.setCode("test2");
        data.setName("测试模块");
        data.setDescription("测试用的模块，一会就删除了");

        datas.add(data);

        data = new Module();
        data.setNamespace("com.windf");
        data.setCode("test3");
        data.setName("测试模块");
        data.setDescription("测试用的模块，一会就删除了");

        datas.add(data);
    }

    /**
     * 获取基本的路径
     * @return
     */
    protected String getBasePath() {
        return "/module";
    }

    protected <T> ResultData<T> analyzeResultData(ResultData resultData, Class<T> clazz) {
        JSONObject jsonData = (JSONObject) resultData.getData();
        T data = jsonData.toJavaObject(clazz);
        resultData.setData(data);

        return resultData;
    }

    @Test
    public void t101Create() {
        System.out.println(1);
        for (Module data : datas) {
            ResponseEntity<ResultData> responseEntity = restTemplate.postForEntity(this.getBasePath() + "/", data, ResultData.class);
            ResultData resultData = responseEntity.getBody();
            Assert.assertEquals("200", resultData.getCode());
        }
    }

    @Test
    public void t201Detail() {
        System.out.println(2);
        ResultData resultData = restTemplate.getForObject(this.getBasePath() + "/{id}", ResultData.class, "com.windf.user");
        resultData = analyzeResultData(resultData, Module.class);
        Module module = (Module) resultData.getData();
        Assert.assertEquals("com.windf.user", module.getId());
    }

    public void t301Update() {
        System.out.println(3);
    }

    public void t401Search() {
        System.out.println(4);
    }

    public void t501Delete() {
        System.out.println(5);
    }

    public void t601DeleteByIds() {
        System.out.println(6);
    }
}

package com.windf;


import com.alibaba.fastjson.JSONObject;
import com.windf.core.entity.Page;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ModuleManageControllerTest {

    /**
     * 修改后的名称
     */
    public static final String UPDATED_NAME = "修改后的名称";

    /**
     * 数据ID
     */
    private String DATA_ID = "com.windf.test";

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * 多个数据，用于创建和多个删除
     */
    private List<Module> dataList = new ArrayList<>();

    /**
     * 主要的数据，用于多个创建
     */
    private Module mainData;

    @Before
    public void readyData() {

        Module data = new Module();
        data.setNamespace("com.windf");
        data.setCode("test");
        data.setName("测试模块");
        data.setDescription("测试用的模块，一会就删除了");

        dataList.add(data);

        data = new Module();
        data.setNamespace("com.windf");
        data.setCode("test2");
        data.setName("测试模块");
        data.setDescription("测试用的模块，一会就删除了");

        dataList.add(data);

        data = new Module();
        data.setNamespace("com.windf");
        data.setCode("test3");
        data.setName("测试模块");
        data.setDescription("测试用的模块，一会就删除了");

        dataList.add(data);

        mainData = dataList.get(0);
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
        if (jsonData != null) {
            T data = jsonData.toJavaObject(clazz);
            resultData.setData(data);
        }

        return resultData;
    }

    @Test
    public void t101Create() {
        for (Module data : dataList) {
            ResponseEntity<ResultData> responseEntity = restTemplate.postForEntity(this.getBasePath() + "/", data, ResultData.class);
            ResultData resultData = responseEntity.getBody();
            Assert.assertEquals(ResultData.CODE_SUCCESS, resultData.getCode());
        }
    }

    @Test
    public void t201Detail() {
        Module module = this.getDataById(DATA_ID);
        Assert.assertEquals(DATA_ID, module.getId());
    }

    @Test
    public void t301Update() {
        // 修改之前
        Module beforeUpdateData = this.getDataById(DATA_ID);
        Assert.assertNotEquals(UPDATED_NAME, beforeUpdateData.getName());

        // 修改，修改名称
        mainData.setName(UPDATED_NAME);
        ResponseEntity<ResultData> responseEntity = restTemplate.postForEntity(this.getBasePath() + "/", mainData, ResultData.class);
        ResultData resultData = responseEntity.getBody();
        Assert.assertEquals(ResultData.CODE_SUCCESS, resultData.getCode());

        // 修改之后
        Module afterUpdateData = this.getDataById(DATA_ID);
        Assert.assertEquals(UPDATED_NAME, afterUpdateData.getName());
    }

    @Test
    public void t401Search() {
        ResultData resultData = restTemplate.getForObject(this.getBasePath() + "/", ResultData.class);
        analyzeResultData(resultData, Page.class);
        Page page = (Page) resultData.getData();
        List<Module> data = page.getData();
        Assert.assertNotNull(data);
    }

    @Test
    public void t501Delete() {
        // 删除之前
        Module beforeUpdateResultData = this.getDataById(DATA_ID);
        Assert.assertNotNull(beforeUpdateResultData);

        // 删除
        restTemplate.delete(this.getBasePath() + "/{id}", DATA_ID);

        // 删除之后
        ResultData afterDeleteResultData = this.getResultById(DATA_ID);
        Assert.assertEquals(ResultData.CODE_NOT_FOUND, afterDeleteResultData.getCode());
    }

    @Test
    public void t601DeleteByIds() {
        String ids = "";
        for (Module data : dataList) {
            if (!data.equals(mainData)) {
                ids += "," + data.getId();
            }
        }
        if (ids.startsWith(",")) {
            ids = ids.substring(1);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);

        restTemplate.delete(this.getBasePath() + "/?ids={ids}", params);

        for (Module data : dataList) {
            if (!data.equals(mainData)) {
                ResultData resultData = this.getResultById(data.getId());
                Assert.assertEquals(ResultData.CODE_NOT_FOUND, resultData.getCode());
            }
        }
    }

    /**
     * 根据id获取结果
     * @param id
     * @return
     */
    protected ResultData getResultById(String id) {
        ResultData resultData = restTemplate.getForObject(this.getBasePath() + "/{id}", ResultData.class, id);
        resultData = analyzeResultData(resultData, Module.class);
        return resultData;
    }

    /**
     * 根据id获取数据
     * @param id
     * @return
     */
    protected Module getDataById(String id) {
        ResultData resultData = this.getResultById(id);
        if (ResultData.CODE_SUCCESS.equals(resultData.getCode())) {
            return (Module) resultData.getData();
        } else {
            return null;
        }
    }
}

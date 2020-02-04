package com.windf;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.windf.core.entity.ResultData;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypeControllerTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    public void listAll() {
        // TODO 这里的type，是需要改成常量的
        ResultData resultData = restTemplate.getForObject("/type/", ResultData.class);
        Assert.assertEquals("能正常查询到数据", ResultData.CODE_SUCCESS, resultData.getCode());

        JSONArray jsonArray = (JSONArray) resultData.getData();
        Assert.assertTrue("能查询到的类型不止5个", jsonArray.size() > 5);
    }

    @Test
    public void listAllBySearch() {
        // TODO 这里的type，是需要改成常量的
        ResultData resultData = restTemplate.getForObject("/type/?key={key}", ResultData.class, "Set");
        Assert.assertEquals("能正常查询到数据", ResultData.CODE_SUCCESS, resultData.getCode());

        JSONArray jsonArray = (JSONArray) resultData.getData();
        Assert.assertTrue("能查询到类型为set的数据", jsonArray.size() > 0);
    }

}

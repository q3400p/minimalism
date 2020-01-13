package com.whaty.cbs.plugins.web.response;

import com.windf.core.entity.ResultData;
import com.windf.core.util.StringUtil;

public class JsonResponseReturn implements ResponseReturn {

    @Override
    public ResultData parameterError() {
        return null;
    }

    @Override
    public ResultData success() {
        return success(RESULT_SUCCESS_MESSAGE);
    }

    @Override
    public ResultData success(String message) {
        return returnData(RESULT_SUCCESS_CODE, message, null);
    }

    @Override
    public ResultData successData(Object data) {
        return returnData(RESULT_SUCCESS_CODE, RESULT_SUCCESS_MESSAGE, data);
    }

    @Override
    public ResultData error(String message) {
        return error(RESULT_NORMAL_ERROR_CODE, message);
    }

    @Override
    public ResultData error(String code, String message) {
        return returnData(code, message, null);
    }

    @Override
    public ResultData returnData(String code, String message, Object data) {
        ResultData resultData = new ResultData();

        // 设置返回的状态
        resultData.setCode(code);

        // 设置返回的数据
        if (data != null) {
            resultData.setData(data);
        }

        // 设置返回的消息
        if (StringUtil.isNotEmpty(message)) {
            resultData.setMessage(message);
        }

        return resultData;
    }

    @Override
    public ResultData redirect(String url) {
        return successData(url);
    }

    @Override
    public ResultData redirectData(String url, Object data) {
        return successData(url);
    }
}

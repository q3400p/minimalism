package com.whaty.cbs.plugins.web.response;

import com.windf.core.entity.ResultData;
import com.windf.core.util.StringUtil;

public abstract class JsonResponseReturn implements ResponseReturn {

    @Override
    public ResultData parameterError() {
        return error("parameter error");
    }

    @Override
    public ResultData success() {
        return returnData(true, "success");
    }

    @Override
    public ResultData error(String tip) {
        return returnData(false, tip);
    }

    @Override
    public ResultData success(String tip) {
        return returnData(true, tip);
    }

    @Override
    public ResultData successData(Object data) {
        return returnData(true, "success", data);
    }

    @Override
    public ResultData errorData(Object data) {
        return returnData(true, "error", data);
    }

    @Override
    public ResultData returnData(Object data) {
        return returnData(true, null, data);
    }

    @Override
    public ResultData returnData(boolean success, String tip) {
        return returnData(success, tip, null);
    }

    @Override
    public ResultData returnData(boolean success, String tip, Object data) {
        ResultData resultData = new ResultData();
        if (success) {
            resultData.setCode("200");
        } else {
            resultData.setCode("500");
        }
        if (data != null) {
            resultData.setData(data);
        }
        if (StringUtil.isNotEmpty(tip)) {
            resultData.setMessage(tip);
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

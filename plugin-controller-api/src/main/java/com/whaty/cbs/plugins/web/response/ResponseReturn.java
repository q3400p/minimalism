package com.whaty.cbs.plugins.web.response;

import com.windf.core.entity.ResultData;

public interface ResponseReturn {
    static final String RESULT_SUCCESS_CODE = "200";
    static final String RESULT_ERROR_CODE = "500";

    /**
     * 设置页面返回，并直接返回
     *
     * @param page
     * @return
     */
    ResultData page(String page);

    /**
     * 返回错误信息
     *
     * @return
     */
    ResultData parameterError();

    /**
     * 返回成功提示
     *
     * @return
     */
    ResultData success();

    /**
     * 返回成功提示
     *
     * @return
     */
    ResultData success(String tip);

    /**
     * 返回错误信息
     *
     * @param tip
     * @return
     */
    ResultData error(String tip);

    /**
     * 返回带数据的成功信息
     *
     * @param data
     * @return
     */
    ResultData successData(Object data);

    /**
     * 返回带数据的错误信息
     *
     * @param data
     * @return
     */
    ResultData errorData(Object data);

    /**
     * 只返回数据
     *
     * @param data
     * @return
     */
    ResultData returnData(Object data);

    /**
     * 返回map信息,没有数据
     *
     * @param success
     * @param tip
     * @return
     */
    ResultData returnData(boolean success, String tip);

    /**
     * 返回带数据和提示的信息
     *
     * @param success
     * @param tip
     * @param data
     * @return
     */
    ResultData returnData(boolean success, String tip, Object data);

    /**
     * 重定向到某个页面
     *
     * @param string
     * @return
     */
    ResultData redirect(String string);

    /**
     * 重定向到某个页面,携带参数
     * @param url
     * @param data
     * @return
     */
    ResultData redirectData(String url, Object data);
}

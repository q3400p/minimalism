package com.whaty.cbs.plugins.web.response;

import com.windf.core.entity.ResultData;

public interface ResponseReturn {
    /**
     * 正常的返回结果code
     */
    String RESULT_SUCCESS_CODE = "200";
    /**
     * 地址重定向的code
     */
    String RESULT_PARAMETER_ERROR_CODE = "302";
    /**
     * 参数错误的code
     */
    String RESULT_REDIRECT_ERROR_CODE = "400";
    /**
     * 通用的错误异常
     */
    String RESULT_NORMAL_ERROR_CODE = "500";

    /**
     * 参数正确的默认message
     */
    String RESULT_SUCCESS_MESSAGE = "success";
    /**
     * 参数错误的默认message
     */
    String RESULT_ERROR_MESSAGE = "error";

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
     * 默认的code为 {@link ResponseReturn#RESULT_SUCCESS_CODE}
     * 默认的data为 null
     *
     * @return
     */
    ResultData success(String message);

    /**
     * 返回带数据的成功信息
     * 默认的code为 {@link ResponseReturn#RESULT_SUCCESS_CODE}
     * 默认的message为code {@link ResponseReturn#RESULT_SUCCESS_MESSAGE}
     *
     * @param data
     * @return
     */
    ResultData successData(Object data);

    /**
     * 返回通用的错误信息
     * 默认的code为 {@link ResponseReturn#RESULT_NORMAL_ERROR_CODE}
     * 默认的data为 null
     *
     * @param message
     * @return
     */
    ResultData error(String message);

    /**
     * 返回指定code的错误信息
     * 默认的data为 null
     *
     * @param code
     * @param message
     * @return
     */
    ResultData error(String code, String message);

    /**
     * 返回带数据和提示的信息
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    ResultData returnData(String code, String message, Object data);

    /**
     * 重定向到某个页面
     *
     * @param url
     * @return
     */
    ResultData redirect(String url);

    /**
     * 重定向到某个页面,携带参数
     * @param url
     * @param data
     * @return
     */
    ResultData redirectData(String url, Object data);
}

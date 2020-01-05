package com.windf.core.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回值，用于接口返回的json格式
 */
public class ResultData implements Serializable{
    public static final String CODE_SUCCESS = "200";
    public static final String CODE_REDIRECT = "302";
    public static final String CODE_BAD_REQUEST = "400";
    public static final String CODE_NOT_FOUND = "404";
    public static final String CODE_NORMAL_ERROR = "500";

    private static final String KEY_CODE = "errCode";   // meta的 code 的key
    private static final String KEY_MESSAGE = "errMsg";   // meta的 message 的key

    private String code; // 0: 失败，1：成功
    private String message; //提示信息
    private Map<String, Object> meta = new HashMap<>(); // 这里是为了兼容管理端的返回格式
    private Object data;// 结果数据

    public ResultData() {

    }

    public ResultData(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;

        this.meta.put(KEY_MESSAGE, message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.meta.put(KEY_MESSAGE, message);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }
}

package com.windf.minimalism.generation.entity;

public class Parameter extends BaseModel {
    /**
     * 方法参数和方法参数类型之间隔离的字符串
     */
    public static final String ID_POINT = "#param_";

    /**
     * 参数数字之间的分隔
     */
    public static final String PARAM_NUMBER_POINT = "#";

    private Type type;
    private String code;
    private String verify;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }
}

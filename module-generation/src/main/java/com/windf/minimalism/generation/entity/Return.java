package com.windf.minimalism.generation.entity;

public class Return extends BaseModel {
    /**
     * 方法和方法返回值之间隔离的字符串
     */
    public static final String ID_POINT = "#return#";

    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

package com.windf.minimalism.generation.entity.type;

import com.windf.minimalism.generation.entity.Type;

public enum LangType {
    SHORT("short", "短整型"),
    INTEGER("integer", "整型"),
    FLOAT("float", "浮点型"),
    DOUBLE("double", "双精度"),
    LONG("long", "长整形"),
    BOOLEAN("boolean", "布尔类型"),
    CHARACTER("char", "字符"),
    BYTE("byte", "字节"),

    VOID("void", "空"),

    STRING("string", "字符串"),
    DECIMAL("decimal", "高精度"),
    LIST("list", "有序列表"),
    SET("set", "无顺列表");

    private String code;
    private String name;
    private Type type;

    LangType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Type getType() {
        if (type == null) {
            LangType langType = this;
            this.type = new BaseType() {

                @Override
                public LangType getLangType() {
                    return langType;
                }
            };
        }

        return type;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

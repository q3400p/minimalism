package com.windf.minimalism.generation.entity.type;

import com.windf.minimalism.generation.entity.Type;

public enum LangType {
    SHORT("Short", "短整型"),
    INTEGER("Integer", "整型"),
    FLOAT("Float", "浮点型"),
    DOUBLE("Double", "双精度"),
    LONG("Long", "长整形"),
    BOOLEAN("Boolean", "布尔类型"),
    CHARACTER("Char", "字符"),
    BYTE("Byte", "字节"),

    VOID("void", "空"),

    STRING("String", "字符串"),
    DECIMAL("Decimal", "高精度"),
    LIST("List", "有序列表"),
    SET("Set", "无顺列表");

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

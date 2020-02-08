package com.windf.minimalism.generation.entity;

// TODO java.lang.*这些好像都是java的，不能这么写，还要放到实现类里
public enum LangType {
    SHORT("Short", "短整型", "java.lang.Short"),
    INTEGER("Integer", "整型", "java.lang.Integer"),
    FLOAT("Float", "浮点型", "java.lang.Float"),
    DOUBLE("Double", "双精度", "java.lang.Double"),
    LONG("Long", "长整形", "java.lang.Long"),
    BOOLEAN("Boolean", "布尔类型", "java.lang.Boolean"),
    CHARACTER("Character", "字符", "java.lang.Character"),
    BYTE("Byte", "字节", "java.lang.Byte"),

    VOID("void", "空", null),

    STRING("String", "字符串", "java.lang.String"),
    DECIMAL("BigDecimal", "高精度", "java.math.BigDecimal"),
    MAP("Map", "有序列表", "java.util.Map"),
    LIST("List", "有序列表", "java.util.List"),
    SET("Set", "无顺列表", "java.util.Set");

    private String code;
    private String name;
    private String id;
    private Type type;

    LangType(String code, String name, String id) {
        this.code = code;
        this.name = name;
        this.id = id;
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

    public String getId() {
        return id;
    }
}

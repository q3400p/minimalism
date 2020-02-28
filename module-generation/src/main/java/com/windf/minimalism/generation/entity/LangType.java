package com.windf.minimalism.generation.entity;

public enum LangType {

    VOID("void", "空"),
    INTEGER("Integer", "整数"),
    Score("Score", "分数"),
    Money("Money", "金额"),
    Boolean("Boolean", "是否"),

    Password("Password", "密码"),
    Phone("Phone", "手机号"),
    IdCard("IdCard", "身份证"),

    Redio("Redio", "单选"),
    Checkbox("Checkbox", "多选框"),
    Combox("Combox", "下拉框"),

    Date("Date", "日期"),
    Time("Time", "时间"),
    Datetime("DateTime", "日期和时间"),

    Field("Field", "普通字符串"),
    TextArea("TextArea", "多行文本"),
    TextEditor("TextEditor", "富文本"),

    File("File", "文件"),
    Image("Image", "图片"),
    Audio("Audio", "音频"),
    Video("Video", "视频");

    private String code;
    private String name;
    private String id;
    private Type type;

    LangType(String code, String name) {
        this.id = code;
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

    public String getId() {
        return id;
    }
}

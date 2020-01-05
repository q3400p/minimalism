package com.windf.minimalism.generation.entity;

import com.windf.core.entity.BaseEntity;

public class ExpandItem extends BaseEntity {
    private String name;            // 名称
    private String code;            // 编号，用于模板中使用
    private Type type;              // 类型
    private Object defaultValue;    // 默认值
    private boolean requested;      // 是否是必填的
    private String layout;          // 当前模块的编号，标志每个拓展所属的模块，防止冲突
    private Object value;           // 用户填写的值

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isRequested() {
        return requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

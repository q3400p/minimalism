package com.windf.minimalism.generation.entity;

import java.util.List;

public class Entity extends BaseModel implements Type {
    private String code;
    private String name;
    private String layout;
    private Module module;
    private List<Field> fields;
    private List<Method> methods;

    @Override
    public String getTypeCode() {
        return this.code;
    }

    @Override
    public String getTypeName() {
        return this.name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }
}

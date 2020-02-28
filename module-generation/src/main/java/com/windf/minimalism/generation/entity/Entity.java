package com.windf.minimalism.generation.entity;

import java.util.ArrayList;
import java.util.List;

public class Entity extends Type {
    private String code;
    private String name;
    private Module module;
    private List<Field> fields = new ArrayList<>();
    private List<Method> methods = new ArrayList<>();

    @Override
    public boolean getIsEntity() {
        return true;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

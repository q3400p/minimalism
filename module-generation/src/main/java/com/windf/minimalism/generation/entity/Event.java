package com.windf.minimalism.generation.entity;

public class Event extends BaseModel {
    private Method sourceMethod;
    private String name;
    private Module module;

    public Method getSourceMethod() {
        return sourceMethod;
    }

    public void setSourceMethod(Method sourceMethod) {
        this.sourceMethod = sourceMethod;
    }

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
}

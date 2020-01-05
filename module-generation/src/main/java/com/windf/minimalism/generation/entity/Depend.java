package com.windf.minimalism.generation.entity;

public class Depend extends BaseModel {
    private Module sourceModule;
    private Module targetModule;

    public Module getSourceModule() {
        return sourceModule;
    }

    public void setSourceModule(Module sourceModule) {
        this.sourceModule = sourceModule;
    }

    public Module getTargetModule() {
        return targetModule;
    }

    public void setTargetModule(Module targetModule) {
        this.targetModule = targetModule;
    }
}

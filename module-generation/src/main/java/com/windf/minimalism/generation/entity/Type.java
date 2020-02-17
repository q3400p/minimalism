package com.windf.minimalism.generation.entity;

public class Type extends BaseModel {

    private String code;
    private String name;

    public Type() {

    }

    public Type(String id) {
        super();
        this.id = id;
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
}

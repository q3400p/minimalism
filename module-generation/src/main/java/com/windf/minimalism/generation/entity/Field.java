package com.windf.minimalism.generation.entity;

public class Field extends BaseModel {
    private String code;
    private String name;
    private Entity entity;
    private Type type;

    public String getId() {
        String id = super.getId();
        if (id == null) {
            id = this.entity.getId() + ID_POINT + this.code;
        }
        return id;
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

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}

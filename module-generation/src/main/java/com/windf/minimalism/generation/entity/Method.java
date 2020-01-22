package com.windf.minimalism.generation.entity;

import java.util.List;

public class Method extends BaseModel {
    private String code;
    private String name;
    private Entity entity;
    private Return methodReturn;
    private List<Parameter> parameters;

    public String getId() {
        return this.entity.getId() + ID_POINT + this.code;
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

    public Return getMethodReturn() {
        return methodReturn;
    }

    public void setMethodReturn(Return methodReturn) {
        this.methodReturn = methodReturn;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}

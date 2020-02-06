package com.windf.minimalism.generation.entity;

// TODO 这里还是需要把各个实体都装进来，方便获取
public class Module extends BaseModel {
    private String namespace;
    private String code;
    private String version;
    private boolean isInterface;
    private String name;
    private String description;

    public String getId() {
        String id = super.getId();
        if (id == null) {
            id = this.namespace + ID_POINT + this.code;
        }
        return id;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

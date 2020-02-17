package ${module.namespace}.module.${module.code}.entity;

/**
 * ${entity.description!}
 */
public class ${entity.classCode} {
  <#list entity.fields as field>
    /**
     * ${field.name}
     * ${field.description!}
     */
    private ${field.type.id} ${field.code};
  </#list>
  <#list entity.fields as field>

    public ${field.type.code} get${field.code?cap_first}() {
        return username;
    }

    public void set${field.code?cap_first}(${field.type.code} ${field.code}) {
        this.${field.code} = ${field.code};
    }
  </#list>
}

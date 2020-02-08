package ${module.namespace}.module.${entity.code}.entity;

<#list entity.imports('com.windf.module.${module.code}.entity') as imp>
import ${imp};
</#list>

/**
 * ${entity.description!}
 */
public class ${entity.classCode} {
  <#list entity.fields as field>
    /**
     * ${field.name}
     * ${field.description!}
     */
    private ${field.type.typeCode} ${field.code};
  </#list>
  <#list entity.fields as field>

    public ${field.type.typeCode} get${field.code?cap_first}() {
        return username;
    }

    public void set${field.code?cap_first}(${field.type.typeCode} ${field.code}) {
        this.${field.code} = ${field.code};
    }
  </#list>
}

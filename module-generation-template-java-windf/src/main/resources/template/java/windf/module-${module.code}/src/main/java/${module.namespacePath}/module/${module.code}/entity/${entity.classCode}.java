package ${module.namespace}.module.${module.code}.entity;

import com.windf.core.entity.BaseEntity;
/**
 * ${entity.name}
 <#if entity.description?? >
 * ${entity.description!}
 </#if>
 */
public class ${entity.classCode} extends BaseEntity {
  <#list entity.fields as field>
  <#if field.code != 'id' && field.code != 'createDate' && field.code != 'updateDate'
        && field.code != 'status'  && field.code != 'siteCode' >
    /**
     * ${field.name}
     <#if field.description?? >
     * ${field.description!}
     </#if>
     */
    private ${field.type.classTypeId} ${field.code};
  </#if>
  </#list>
  <#list entity.fields as field>
  <#if field.code != 'id' && field.code != 'createDate' && field.code != 'updateDate'
        && field.code != 'status'  && field.code != 'siteCode' >

    public ${field.type.classTypeId} get${field.code?cap_first}() {
        return ${field.code};
    }

    public void set${field.code?cap_first}(${field.type.classTypeId} ${field.code}) {
        this.${field.code} = ${field.code};
    }
  </#if>
  </#list>
}

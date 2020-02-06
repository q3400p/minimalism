package com.windf.module.${module.code}.repository;

<#list entity.imports('com.windf.module.${module.code}.repository') as imp>
import ${imp};
</#list>

public interface ${entity.code?cap_first}Repository {
  <#list entity.methods as method>
    /**
     * ${method.name!}
     * ${method.description!}
    <#list method.parameters as param>
     * @param ${param.code} ${param.name!param.description!}
    </#list>
     */
    ${method.methodReturn.type.typeCode} ${method.code}(<#list method.parameters as param >${param.type.typeCode} ${param.code}<#if param_has_next>, </#if></#list>);
  </#list>
}

package com.windf.module.${module.code}.service;

<#list entity.imports('com.windf.module.${module.code}.service') as imp>
import ${imp};
</#list>

/**
 * ${entity.description!}提供的服务
 */
public interface ${entity.classCode}Service {
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

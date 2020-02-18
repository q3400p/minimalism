package ${module.namespace}.module.${module.code}.service;

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
    ${method.methodReturn.type.classTypeId} ${method.code}(<#list method.parameters as param >${param.type.classTypeId} ${param.code}<#if param_has_next>, </#if></#list>);
  </#list>
}

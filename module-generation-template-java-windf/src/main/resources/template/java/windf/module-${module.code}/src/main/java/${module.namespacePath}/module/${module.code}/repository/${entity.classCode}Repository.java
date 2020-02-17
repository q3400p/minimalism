package ${module.namespace}.module.${module.code}.repository;

public interface ${entity.code?cap_first}Repository {
  <#list entity.methods as method>
    /**
     * ${method.name!}
     * ${method.description!}
    <#list method.parameters as param>
     * @param ${param.code} ${param.name!param.description!}
    </#list>
     */
    ${method.methodReturn.type.id} ${method.code}(<#list method.parameters as param >${param.type.code} ${param.code}<#if param_has_next>, </#if></#list>);
  </#list>
}

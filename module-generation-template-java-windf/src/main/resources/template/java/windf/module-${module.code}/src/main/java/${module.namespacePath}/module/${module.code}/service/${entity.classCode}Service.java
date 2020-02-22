package ${module.namespace}.module.${module.code}.service;

<#if entity.manageAble >
import com.windf.core.service.ManageService;
</#if>

/**
 * ${entity.description!}提供的服务
 */
public interface ${entity.classCode}Service <#if entity.manageAble >extends ManageService<${entity.classTypeId}> </#if>{
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

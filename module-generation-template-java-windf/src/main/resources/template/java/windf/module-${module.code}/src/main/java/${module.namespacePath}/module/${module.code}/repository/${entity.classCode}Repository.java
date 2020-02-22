package ${module.namespace}.module.${module.code}.repository;
<#if entity.manageAble >
import com.windf.core.repository.ManageRepository;
</#if>

public interface ${entity.classCode}Repository <#if entity.manageAble >extends ManageRepository<${entity.classTypeId}> </#if>{
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

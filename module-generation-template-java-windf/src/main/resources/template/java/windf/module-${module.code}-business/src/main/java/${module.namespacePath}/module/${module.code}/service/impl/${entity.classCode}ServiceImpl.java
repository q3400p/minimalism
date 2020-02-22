package ${module.namespace}.module.${module.code}.service.impl;

<#if entity.tableAble >
import com.windf.module.user.repository.${entity.classCode}Repository;
</#if>
import com.windf.module.user.service.${entity.classCode}Service;
<#if entity.tableAble >
import org.springframework.beans.factory.annotation.Autowired;
</#if>
<#if entity.manageAble >
import com.windf.core.repository.ManageRepository;
import com.windf.plugin.service.business.BaseManageService;
</#if>
import org.springframework.stereotype.Service;

@Service
public class ${entity.classCode}ServiceImpl <#if entity.manageAble>extends BaseManageService<${entity.classTypeId}> </#if>implements ${entity.classCode}Service {

  <#if entity.tableAble >
    @Autowired
    private ${entity.classCode}Repository ${entity.code}Repository;
  </#if>

  <#if entity.manageAble >
    @Override
    public ManageRepository<${entity.classTypeId}> getManageRepository() {
      <#if entity.tableAble >
        return this.${entity.code}Repository;
      <#else>
        return null;
      </#if>
    }
  </#if>

  <#list entity.methods as method>

    @Override
    public ${method.methodReturn.type.classTypeId} ${method.code}(<#list method.parameters as param >${param.type.classTypeId} ${param.code}<#if param_has_next>, </#if></#list>) {
      // TODO 补全方法
      <#if method.methodReturn.type.code != 'void' >
        return null;
      </#if>
    }
  </#list>
}

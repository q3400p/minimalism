package ${module.namespace}.module.${module.code}.controller.api;

import com.windf.core.entity.ResultData;
import com.windf.module.user.service.${entity.classCode}Service;
<#if entity.manageAble >
import com.windf.core.service.ManageService;
import com.windf.plugin.controller.api.controller.BaseManageController;
<#else>
import com.windf.plugin.controller.api.controller.BaseController;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(${entity.classCode}Controller.API_PATH)
public class ${entity.classCode}Controller extends <#if entity.manageAble >BaseManageController<${entity.classTypeId}><#else>BaseController</#if> {
    public static final String API_PATH = BASE_API_PATH + "/${entity.code}";

    @Autowired
    private ${entity.classCode}Service ${entity.code}Service;

  <#if entity.manageAble >
    @Override
    public ManageService<${entity.classTypeId}> getManageService() {
      <#if entity.tableAble >
        return this.${entity.code}Service;
      <#else>
        return null;
      </#if>
    }
  </#if>

  <#list entity.methods as method>
  <#if method.webMethodAble >

    @RequestMapping("/${method.code}")
    public ResultData ${method.code}(<#list method.parameters as param >${param.type.classTypeId} ${param.code}<#if param_has_next>, </#if></#list>) {
      <#if method.methodReturn.type.code == 'void' >
        ${entity.code}Service.${method.code}(<#list method.parameters as param >${param.code}<#if param_has_next>, </#if></#list>);

        return response().success();
      <#else>
        ${method.methodReturn.type.classTypeId} data = ${entity.code}Service.${method.code}(<#list method.parameters as param >${param.code}<#if param_has_next>, </#if></#list>);

        return response().successData(data);
      </#if>
    }
  </#if>
  </#list>
}

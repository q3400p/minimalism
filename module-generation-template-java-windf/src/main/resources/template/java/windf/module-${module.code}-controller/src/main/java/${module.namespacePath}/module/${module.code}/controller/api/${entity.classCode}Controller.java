package ${module.namespace}.module.${module.code}.controller.api;

import com.windf.core.entity.ResultData;
import com.windf.module.user.service.${entity.classCode}Service;
import com.windf.plugin.controller.api.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(${entity.classCode}Controller.API_PATH)
public class ${entity.classCode}Controller extends BaseController {
    public static final String API_PATH = BASE_API_PATH + "/${entity.code}";

    @Autowired
    private ${entity.classCode}Service ${entity.code}Service;

    <#list entity.methods as method>

    public ResultData ${method.code}(<#list method.parameters as param >${param.type.classTypeId} ${param.code}<#if param_has_next>, </#if></#list>) {

    }
    </#list>
}

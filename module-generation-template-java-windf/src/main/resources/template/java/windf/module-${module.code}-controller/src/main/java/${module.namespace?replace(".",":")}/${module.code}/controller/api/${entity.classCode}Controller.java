package ${module.namespace}.${module.code}.controller.api;

import com.windf.core.entity.ResultData;
import ${module.namespace}.${module.code}.entity.${entity.code?cap_first};
import ${module.namespace}.${module.code}.entity.service.${entity.code?cap_first}Service;
import com.windf.plugin.controller.api.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(${entity.code?cap_first}Controller.API_PATH)
public class ${entity.code?cap_first}Controller extends BaseController {
    public static final String API_PATH = BASE_API_PATH + "/${entity.code}";

    @Autowired
    private ${entity.code?cap_first}Service ${entity.code}Service;

    <#list entity.methods as method>

    @Override
    public ResultData ${method.code}(<#list method.parameters as param >${param.type.typeCode} ${param.code}<#if param_has_next>, </#if></#list>) {

    }
    </#list>
}

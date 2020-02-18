package ${module.namespace}.module.${module.code}.service.impl;

import ${module.namespace}.module.${module.code}.repository.${entity.code};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${entity.classCode}ServiceImpl implements ${entity.classCode}Service {

    @Autowired
    private ${entity.classCode}Repository ${entity.code}Repository;

  <#list entity.methods as method>

    @Override
    public ${method.methodReturn.type.classTypeId} ${method.code}(<#list method.parameters as param >${param.type.classTypeId} ${param.code}<#if param_has_next>, </#if></#list>) {
    }
  </#list>
}

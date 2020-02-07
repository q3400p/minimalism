package com.windf.module.${module.code}.service.impl;


<#list entity.imports('com.windf.module.${module.code}.service.impl') as imp>
import ${imp};
</#list>
import com.windf.module.${module.code}.repository.${entity.code};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${entity.code?cap_first}ServiceImpl implements ${entity.code?cap_first}Service {

    @Autowired
    private ${entity.code?cap_first}Repository ${entity.code}Repository;

  <#list entity.methods as method>

    @Override
    public ${method.methodReturn.type.typeCode} ${method.code}(<#list method.parameters as param >${param.type.typeCode} ${param.code}<#if param_has_next>, </#if></#list>) {
    }
  </#list>
}

package ${module.namespace}.module.${module.code}.repository.mysql;

import ${module.namespace}.module.${module.code}.repository.${entity.classCode}Repository;
import com.windf.plugin.repository.mysql.BaseMysqlRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ${entity.classCode}RepositoryImpl extends BaseMysqlRepository implements ${entity.classCode}Repository {

    <#list entity.methods as method>

    @Override
    public ${method.methodReturn.type.classTypeId} ${method.code}(<#list method.parameters as param >${param.type.classTypeId} ${param.code}<#if param_has_next>, </#if></#list>) {
        // 数据库操作
      <#if method.methodReturn.type.code != 'void' >
        return null;
      </#if>
    }
    </#list>
}

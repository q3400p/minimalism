package ${module.namespace}.module.${module.code}.repository.mysql;

import ${module.namespace}.${module.code}.entity.${entity.code?cap_first};
import com.windf.plugin.repository.mysql.BaseMysqlRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ${entity.code?cap_first}RepositoryImpl extends BaseMysqlRepository implements ${entity.code?cap_first}Repository {

    <#list entity.methods as method>

    @Override
    public ${method.methodReturn.type.typeCode} ${method.code}(<#list method.parameters as param >${param.type.typeCode} ${param.code}<#if param_has_next>, </#if></#list>) {
        // 数据库操作
    }
    </#list>
}

package ${module.namespace}.module.${module.code}.repository.mysql;

import ${module.namespace}.module.${module.code}.repository.${entity.classCode}Repository;
import com.windf.core.entity.SearchData;
import com.windf.core.entity.Page;
import com.windf.core.exception.DataAccessException;
import com.windf.plugin.repository.mysql.BaseMysqlRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ${entity.classCode}RepositoryImpl extends BaseMysqlRepository implements ${entity.classCode}Repository {

    @Override
    public void create(final ${entity.classTypeId} ${entity.code}) {

        StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO " );
        sql.append("   ${entity.tableName} ( ");
        sql.append("        create_date,   ");
        sql.append("        update_date,   ");
        sql.append("        status,   ");
        sql.append("        site_code,   ");
      <#list entity.fields as field>
        sql.append("        ${field.tableFieldName}<#if field_index + 1 != entity.fields?size >,</#if>   ");
      </#list>
        sql.append("  ) VALUES (now(), now(), ?, ?, <@compress single_line=true>
                                        <#list entity.fields as field>
                                            ?<#if field_index + 1 != entity.fields?size >,</#if>
                                        </#list></@compress>)");

        jdbcTemplate.execute(sql.toString(),
                new PreparedStatementCallback<Boolean>() {
                    @Nullable
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        ps.setString(1, ${entity.code}.getStatus());
                        ps.setString(2, ${entity.code}.getSiteCode());
                      <#list entity.fields as field>
                       <#if field.type.isEntity >
                        ps.setObject(${field_index + 3}, ${entity.code}.get${field.code?cap_first}());
                       <#elseif field.type.code == 'DateTime' >
                        ps.setTimestamp(${field_index + 3}, new [package||java.sql.Timestamp||Timestamp](${entity.code}.get${field.code?cap_first}().getTime()));
                       <#elseif field.type.code == 'Date' >
                        ps.setData(${field_index + 3}, new [package||java.sql.Date||Date](${entity.code}.get${field.code?cap_first}().getTime()));
                       <#elseif field.type.code == 'Time' >
                        ps.setTime(${field_index + 3}, new [package||java.sql.Time||Time](${entity.code}.get${field.code?cap_first}().getTime()));
                       <#else>
                        ps.set${field.type.classTypeId}(${field_index + 3}, ${entity.code}.get${field.code?cap_first}());
                       </#if>
                      </#list>
                        return ps.executeUpdate() > 0;
                    }
                });
    }

    @Override
    public void update(final ${entity.classCode} ${entity.code}) {

        StringBuffer sql = new StringBuffer();
        sql.append(" UPDATE " );
        sql.append("   ${entity.tableName}  ");
        sql.append(" SET   ");
      <#list entity.fields as field>
        sql.append("   ${field.tableFieldName} = ?,  ");
      </#list>
        sql.append("   update_date = now()   ");
        sql.append(" WHERE id = ? ");

        jdbcTemplate.execute(sql.toString(),
                new PreparedStatementCallback<Boolean>() {
                    @Nullable
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                      <#list entity.fields as field>
                       <#if field.type.isEntity >
                        ps.setString(${field_index + 1}, ${entity.code}.get${field.code?cap_first}().getId());
                       <#elseif field.type.code == 'DateTime' >
                        ps.setTimestamp(${field_index + 1}, new [package||java.sql.Timestamp||Timestamp](${entity.code}.get${field.code?cap_first}().getTime()));
                       <#elseif field.type.code == 'Date' >
                        ps.setData(${field_index + 1}, new [package||java.sql.Date||Date](${entity.code}.get${field.code?cap_first}().getTime()));
                       <#elseif field.type.code == 'Time' >
                        ps.setTime(${field_index + 1}, new [package||java.sql.Time||Time](${entity.code}.get${field.code?cap_first}().getTime()));
                       <#else>
                        ps.set${field.type.classTypeId}(${field_index + 1}, ${entity.code}.get${field.code?cap_first}());
                       </#if>
                      </#list>
                        ps.setString(${entity.fields?size + 1}, ${entity.code}.getId());
                        return ps.executeUpdate() > 0;
                    }
                });
    }

    @Override
    public void delete(List<String> ids) {
        jdbcTemplate.update("delete from ${entity.tableName} where id in (?)", new Object[]{ids});
    }

    @Override
    public ${entity.classCode} detail(String id) {

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT " );
        sql.append("   id, " );
      <#list entity.fields as field>
        sql.append("   ${field.tableFieldName}, " );
      </#list>
        sql.append("   create_date, " );
        sql.append("   update_date, " );
        sql.append("   site_code, " );
        sql.append("   status " );
        sql.append("  FROM ${entity.tableName} " );
        sql.append(" WHERE id = ? " );

        List<${entity.classCode}> ${entity.code}s = jdbcTemplate.query(sql.toString(),
                new BeanPropertyRowMapper<>(${entity.classCode}.class),
                new Object[]{id});

        return ${entity.code}s == null || ${entity.code}s.size() == 0? null: ${entity.code}s.get(0);
    }

    @Override
    public Page<${entity.classCode}> search(SearchData searchData) {
        return null;
    }

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

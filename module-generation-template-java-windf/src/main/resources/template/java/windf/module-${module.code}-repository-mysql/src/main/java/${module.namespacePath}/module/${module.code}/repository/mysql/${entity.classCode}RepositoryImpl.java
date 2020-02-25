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
        sql.append("        id,   ");
        sql.append("        create_date,   ");
        sql.append("        update_date,   ");
        sql.append("        status,   ");
        sql.append("        site_code,   ");
      <#list entity.fields as field>
        sql.append("        ${field.tableFieldName}<#if field_index + 1 != entity.fields?size >,</#if>   ");
      </#list>
        sql.append("  ) VALUES (?, now(), now(), ?, ?, <@compress single_line=true>
                                        <#list entity.fields as field>
                                            ?<#if field_index + 1 != entity.fields?size >,</#if>
                                        </#list></@compress>)");

        jdbcTemplate.execute(sql.toString(),
                new PreparedStatementCallback<Boolean>() {
                    @Nullable
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        ps.setString(1, user.getId());
                        ps.setString(2, user.getStatus());
                        ps.setString(3, user.getSiteCode());
                      <#list entity.fields as field>
                        ps.set${field.type.classTypeId}(${field_index + 4}, ${entity.code}.get${field.code?cap_first}());
                      </#list>
                        return ps.executeUpdate() > 0;
                    }
                });
    }

    @Override
    public void update(User user) {

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
                        ps.setString(1, user.getId());
                        ps.setString(2, user.getStatus());
                        ps.setString(3, user.getSiteCode());
                      <#list entity.fields as field>
                        ps.set${field.type.classTypeId}(${field_index + 4}, ${entity.code}.get${field.code?cap_first}());
                      </#list>
                    return ps.executeUpdate() > 0;
                }
        });

        jdbcTemplate.execute(sql.toString(),
                new PreparedStatementCallback<Boolean>() {
                    @Nullable
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                      <#list entity.fields as field>
                        ps.set${field.type.classTypeId}(${field_index + 1}, ${entity.code}.get${field.code?cap_first}());
                      </#list>
                        ps.setString(${entity.fields?size + 1}, user.getId());
                        return ps.executeUpdate() > 0;
                    }
                });
    }

    @Override
    public void delete(List<String> ids) {
        jdbcTemplate.update("delete from ${entity.tableName} where id in (?)", new Object[]{ids});
    }

    @Override
    public User detail(String id) {

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

        List<User> users = jdbcTemplate.query(sql.toString(),
                new BeanPropertyRowMapper<>(User.class),
                new Object[]{id});

        return users == null || users.size() == 0? null: users.get(0);
    }

    @Override
    public Page<User> search(SearchData searchData) {
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

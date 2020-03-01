package ${module.namespace}.module.${module.code}.repository.mysql;

import ${module.namespace}.module.${module.code}.repository.${entity.classCode}Repository;
import com.windf.core.entity.OrderItem;
import com.windf.core.entity.SearchData;
import com.windf.core.entity.Page;
import com.windf.core.exception.DataAccessException;
import com.windf.core.util.StringUtil;
import com.windf.plugin.repository.mysql.BaseMysqlRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                        ps.setString(${field_index + 3}, ${entity.code}.get${field.code?cap_first}().getId());
                       <#elseif field.type.code == 'DateTime' >
                        ps.setTimestamp(${field_index + 3}, new [package||java.sql.Timestamp||Timestamp](${entity.code}.get${field.code?cap_first}().getTime()));
                       <#elseif field.type.code == 'Date' >
                        ps.setDate(${field_index + 3}, new [package||java.sql.Date||Date](${entity.code}.get${field.code?cap_first}().getTime()));
                       <#elseif field.type.code == 'Time' >
                        ps.setTime(${field_index + 3}, new [package||java.sql.Time||Time](${entity.code}.get${field.code?cap_first}().getTime()));
                       <#elseif field.type.code == 'Integer' >
                        ps.setInt(${field_index + 3}, ${entity.code}.get${field.code?cap_first}());
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
                        ps.setDate(${field_index + 1}, new [package||java.sql.Date||Date](${entity.code}.get${field.code?cap_first}().getTime()));
                       <#elseif field.type.code == 'Time' >
                        ps.setTime(${field_index + 1}, new [package||java.sql.Time||Time](${entity.code}.get${field.code?cap_first}().getTime()));
                       <#elseif field.type.code == 'Integer' >
                        ps.setInt(${field_index + 1}, ${entity.code}.get${field.code?cap_first}());
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
        jdbcTemplate.update("delete from ${entity.tableName} where id in (?)", ids.toArray());
    }

    @Override
    public ${entity.classCode} detail(String id) {

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT " );
        sql.append(this.getSelectSql());
        sql.append("  FROM ${entity.tableName} " );
        sql.append(" WHERE id = ? " );

        List<${entity.classCode}> ${entity.code}s = jdbcTemplate.query(sql.toString(),
                new ${entity.classCode}RowMapper(),
                new Object[]{id});

        return ${entity.code}s == null || ${entity.code}s.size() == 0? null: ${entity.code}s.get(0);
    }

    @Override
    public Page<${entity.classCode}> search(SearchData searchData) {
        Page<${entity.classCode}> page;

        // 如果没有搜索，设置默认搜索
        if (searchData == null) {
            searchData = SearchData.getEmptySearchData();
        }

        // 分页设置
        if (searchData.getPage() != null) {
            page = new Page<${entity.classCode}>(searchData.getPage());
        } else {
            page = new Page<${entity.classCode}>();
        }

        // 查询参数
        List<Object> paramList = new ArrayList<>();

        // 拼接查询的sql
        String whereSql = this.getWhereSql(searchData.getCondition(), paramList);

        // 查询总数
        StringBuffer countSql = new StringBuffer();
        countSql.append(" SELECT ");
        countSql.append("   count(id) ");
        countSql.append(" FROM ${entity.tableName} " );
        countSql.append(" WHERE 1=1" ).append(whereSql);
        Long totalCount = jdbcTemplate.queryForObject(countSql.toString(), paramList.toArray(), Long.class);
        page.setTotal(totalCount);

        // 拼接排序sql
        String orderSql = this.getOrderSql(searchData.getOrder());

        // 拼接分页参数
        if (!page.needNotPage()) {
            paramList.add(page.getStartIndex());
            paramList.add(page.getPageSize());
        }

        // 分页查询数据
        StringBuffer listSql = new StringBuffer();
        listSql.append(" SELECT ");
        listSql.append(this.getSelectSql());
        listSql.append(" FROM ${entity.tableName} ");
        listSql.append(" WHERE 1=1").append(whereSql);
        listSql.append(" ORDER BY ").append(orderSql);
        if (!page.needNotPage()) {
            listSql.append(" LIMIT ?,?");
        }
        List<${entity.classCode}> trains = jdbcTemplate.query(listSql.toString(),
                new ${entity.classCode}RowMapper(),
                paramList.toArray());
        page.setData(trains);

        return page;
    }

    /**
     * 拼接select参数
     */
    private String getSelectSql() {
        StringBuffer sql = new StringBuffer();
        sql.append("   id, " );
      <#list entity.fields as field>
        sql.append("   ${field.tableFieldName}, " );
      </#list>
        sql.append("   create_date, " );
        sql.append("   update_date, " );
        sql.append("   site_code, " );
        sql.append("   status " );
        return sql.toString();
    }

    /**
     * 拼接where
     * @param condition
     * @return
     */
    private String getWhereSql(Map<String, Object> condition, List<Object> paramList) {
        StringBuffer whereSql = new StringBuffer();

        if (condition != null) {
          <#list entity.fields as field>
            String ${field.code} = (String) condition.get("${field.code}");
            if (StringUtil.isNotBlank(${field.code})) {
                whereSql.append(" AND ${field.tableFieldName} = ? ");
                paramList.add(${field.code});
            }
          </#list>
        }

        return whereSql.toString();
    }

    @Override
    protected Map<String,String> getFieldMap() {
        Map<String, String> fieldMap = new HashMap<>();

      <#list entity.fields as field>
        fieldMap.put("${field.code}", "${field.tableFieldName}");
      </#list>

        return fieldMap;
    }

    /**
     * 设置映射关系
     */
    private class ${entity.classCode}RowMapper implements RowMapper<${entity.classCode}> {

        @Override
        public ${entity.classCode} mapRow(ResultSet resultSet, int i) throws SQLException {
            ${entity.classCode} ${entity.code} = new ${entity.classCode}();
            ${entity.code}.setId(resultSet.getString("id"));
          <#list entity.fields as field>
           <#if field.type.isEntity >
            ${field.type.classTypeId} ${field.code} = new ${field.code?cap_first}();
            ${field.code}.setId(resultSet.getString("${field.tableFieldName}"));
            ${entity.code}.set${field.code?cap_first}(${field.code});
           <#elseif field.type.code == 'DateTime' >
            ${entity.code}.set${field.code?cap_first}(resultSet.getTimestamp("${field.tableFieldName}"));
           <#elseif field.type.code == 'Date' >
            ${entity.code}.set${field.code?cap_first}(resultSet.getDate("${field.tableFieldName}"));
           <#elseif field.type.code == 'Time' >
            ${entity.code}.set${field.code?cap_first}(resultSet.getTime("${field.tableFieldName}"));
           <#elseif field.type.code == 'Integer' >
            ${entity.code}.set${field.code?cap_first}(resultSet.getInt("${field.tableFieldName}"));
           <#else>
            ${entity.code}.set${field.code?cap_first}(resultSet.get${field.type.classTypeId}("${field.tableFieldName}"));
           </#if>
          </#list>
            ${entity.code}.setCreateDate(resultSet.getTimestamp("create_date"));
            ${entity.code}.setUpdateDate(resultSet.getTimestamp("update_date"));
            ${entity.code}.setStatus(resultSet.getString("status"));
            ${entity.code}.setSiteCode(resultSet.getString("site_code"));
            return ${entity.code};
        }
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

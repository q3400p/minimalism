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
        sql.append("  FROM ${entity.tableName} t " );
        sql.append(this.getJoinSql());
        sql.append(" WHERE t.id = ? " );

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
        countSql.append("   count(t.id) ");
        countSql.append(" FROM ${entity.tableName} t " );
        countSql.append(this.getJoinSql());
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
        listSql.append(" FROM ${entity.tableName} t ");
        listSql.append(this.getJoinSql());
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
  <#macro selectSql entity, entityAlias>
        sql.append("   ${entityAlias}.id, " );
        sql.append("   ${entityAlias}.create_date, " );
        sql.append("   ${entityAlias}.update_date, " );
        sql.append("   ${entityAlias}.site_code, " );
        sql.append("   ${entityAlias}.status, " );
   <#list entity.fields as field>
    <#if field.type.isEntity>
     <#assign fieldCode = ((entityAlias == 't')?string(field.code, entityAlias + field.code?cap_first))>
     <@selectSql field.type, fieldCode />
    <#else>
        sql.append("   ${entityAlias}.${field.tableFieldName}, " );
    </#if>
   </#list>
  </#macro>

        StringBuffer sql = new StringBuffer();
        <@selectSql entity, 't' />
        sql.append("   '' " );
        return sql.toString();
    }

    private String getJoinSql() {
        StringBuffer sql = new StringBuffer();
      <#macro joinSql entity,parentAlias, parentTableFieldName>
        <#-- entityCode设置 -->
        <#assign entityCode = ((parentAlias == 't')?string(entity.code, parentAlias + entity.code?cap_first))>
        sql.append(" left join ${entity.tableName} ${entityCode} on ${entityCode}.id = ${parentAlias}.${parentTableFieldName} " );
        <#list entity.fields as field>
        <#if field.type.isEntity >
          <@joinSql field.type, entityCode, field.tableFieldName />
        </#if>
       </#list>
      </#macro>

      <#list entity.fields as field>
       <#if field.type.isEntity >
        <@joinSql field.type, 't', field.tableFieldName />
       </#if>
      </#list>

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
                whereSql.append(" AND t.${field.tableFieldName} = ? ");
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

        <#macro mapRow entity, alias, parentAlias>
        <#-- 创建对象，设置基本属性 -->
            ${entity.classTypeId} ${alias} = new ${entity.classCode}();
            ${alias}.setId(resultSet.getString("${parentAlias}.id"));
            ${alias}.setCreateDate(resultSet.getTimestamp("${parentAlias}.create_date"));
            ${alias}.setUpdateDate(resultSet.getTimestamp("${parentAlias}.update_date"));
            ${alias}.setStatus(resultSet.getString("${parentAlias}.status"));
            ${alias}.setSiteCode(resultSet.getString("${parentAlias}.site_code"));
         <#-- 先输出非实体属性 -->
         <#list entity.fields as field>
          <#if field.type.isEntity >
          <#elseif field.type.code == 'DateTime' >
            ${alias}.set${field.code?cap_first}(resultSet.getTimestamp("${parentAlias}.${field.tableFieldName}"));
          <#elseif field.type.code == 'Date' >
            ${alias}.set${field.code?cap_first}(resultSet.getDate("${parentAlias}.${field.tableFieldName}"));
          <#elseif field.type.code == 'Time' >
            ${alias}.set${field.code?cap_first}(resultSet.getTime("${parentAlias}.${field.tableFieldName}"));
          <#elseif field.type.code == 'Integer' >
            ${alias}.set${field.code?cap_first}(resultSet.getInt("${parentAlias}.${field.tableFieldName}"));
          <#else>
            ${alias}.set${field.code?cap_first}(resultSet.get${field.type.classTypeId}("${parentAlias}.${field.tableFieldName}"));
          </#if>
        </#list>
        <#-- 实体属性 -->
        <#list entity.fields as field>
          <#if field.type.isEntity >

            // 设置${field.name}
            <#-- 设置fieldCode -->
            <#assign fieldCode = ((parentAlias == 't')?string(field.code, parentAlias + field.code?cap_first))>
            <@mapRow field.type, fieldCode, fieldCode />
            <#-- fieldCode重置，防止fieldCode被改变 -->
            <#assign fieldCode = ((parentAlias == 't')?string(field.code, parentAlias + field.code?cap_first))>

            ${alias}.set${field.code?cap_first}(${fieldCode});
          </#if>
        </#list>
        </#macro>


        @Override
        public ${entity.classCode} mapRow(ResultSet resultSet, int i) throws SQLException {
            <@mapRow entity, entity.code, 't' />
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

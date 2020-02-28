SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

<#list entities as entity >

-- ----------------------------
-- Table structure for ${entity.tableName}
-- ----------------------------
CREATE TABLE `${entity.tableName}` (
  `id` bigint(255) NOT NULL,
 <#list entity.fields as field>
  `${field.tableFieldName}` ${field.tableFieldType}<#if field.tableFieldLength??>(${field.tableFieldLength})</#if> COMMENT '${field.name}',
 </#list>
  `create_date` datetime COMMENT '创建时间',
  `update_date` datetime COMMENT '修改时间',
  `status` varchar(50) COMMENT '状态',
  `site_code` varchar(50) COMMENT '站点编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT;
</#list>

SET FOREIGN_KEY_CHECKS = 1;

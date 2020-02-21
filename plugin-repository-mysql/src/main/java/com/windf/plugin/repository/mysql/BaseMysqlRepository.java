package com.windf.plugin.repository.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 基本的mysq的存储
 */
@Repository
public class BaseMysqlRepository {

    @Autowired
    protected JdbcTemplate jdbcTemplate;
}

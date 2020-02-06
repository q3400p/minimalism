package com.windf.module.user.repository.mysql;

import com.windf.module.user.entity.User;
import com.windf.module.user.repository.LoginRepository;
import com.windf.plugin.repository.mysql.BaseMysqlRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginRepositoryImpl extends BaseMysqlRepository implements LoginRepository {

    @Override
    public User getByUsername(String loginId) {
        // TODO 需要sql防注入
        List<User> users = jdbcTemplate.query(
                "select id, username, password, name from sys_user where username = '" + loginId + "'",
                new BeanPropertyRowMapper<>(User.class));
        return users == null || users.size() == 0? null: users.get(0);
    }
}

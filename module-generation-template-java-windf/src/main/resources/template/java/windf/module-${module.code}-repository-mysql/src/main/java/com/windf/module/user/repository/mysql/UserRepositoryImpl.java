package com.windf.module.user.repository.mysql;

import com.windf.core.entity.Page;
import com.windf.core.entity.SearchData;
import com.windf.plugin.repository.mysql.BaseManageMysqlRepository;
import com.windf.module.user.entity.User;
import com.windf.module.user.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl extends BaseManageMysqlRepository<User> implements UserRepository {

    @Override
    public void create(User user) {
        jdbcTemplate.execute("INSERT INTO sso_user(name) VALUES (?);",
                new PreparedStatementCallback<Boolean>() {

                    @Nullable
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, user.getUsername());
                        return preparedStatement.executeUpdate() > 0;
                    }
                });
    }

    @Override
    public void update(User user) {
        jdbcTemplate.execute("update sso_user(name) VALUES (?);",
                new PreparedStatementCallback<Boolean>() {
                    @Nullable
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, user.getUsername());
                        return preparedStatement.executeUpdate() > 0;
                    }
                });
    }

    @Override
    public void delete(List<String> ids) {
        jdbcTemplate.update("delete from sso_user where id in (?)", new Object[]{ids});
    }

    @Override
    public User detail(String id) {
        // TODO 需要sql防注入
        List<User> users = jdbcTemplate.query(
                "select id, name from sso_user where id = '" + id + "'",
                new BeanPropertyRowMapper<>(User.class));
        return users == null || users.size() == 0? null: users.get(0);
    }

    @Override
    public Page<User> search(SearchData searchData) {
        return null;
    }
}

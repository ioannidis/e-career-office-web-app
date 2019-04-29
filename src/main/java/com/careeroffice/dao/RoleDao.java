package com.careeroffice.dao;

import com.careeroffice.database.Queries;
import com.careeroffice.database.QueryCallback;
import com.careeroffice.database.QueryParamCallback;
import com.careeroffice.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao implements CrudDao<Role, String> {
    @Override
    public Role findOne(String id) {

        return (Role) Queries.execute("SELECT * FROM roles WHERE id=?", new QueryParamCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, id);
            }

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new Role(
                            resultSet.getString("id"),
                            resultSet.getString("title"));
                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public List<Role> findAll() {

        return (List<Role>) Queries.execute("SELECT * FROM roles", new QueryCallback() {
            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                List<Role> roles = new ArrayList<>();

                while (resultSet.next()) {
                    roles.add(new Role(
                            resultSet.getString("id"),
                            resultSet.getString("title")));
                }

                return roles;
            }
        });

    }

    @Override
    public boolean save(Role obj) {
        return false;
    }

    @Override
    public boolean update(Role obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public int count() {
        return (int) Queries.execute("SELECT COUNT(*) FROM roles", new QueryCallback() {
            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
            }
        });
    }
}

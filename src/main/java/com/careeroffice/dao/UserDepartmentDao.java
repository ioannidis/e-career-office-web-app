package com.careeroffice.dao;

import com.careeroffice.database.Queries;
import com.careeroffice.database.QueryParamCallback;
import com.careeroffice.model.UserDepartment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDepartmentDao implements CrudDao<UserDepartment, String> {

    @Override
    public UserDepartment findOne(String id) {
        return Queries.execute("SELECT * FROM user_department WHERE username=?", new QueryParamCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, id);
            }

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new UserDepartment(
                            resultSet.getString("username"),
                            resultSet.getString("department_id")
                    );
                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public List<UserDepartment> findAll() {
        return null;
    }

    @Override
    public boolean save(UserDepartment obj) {
        return false;
    }

    @Override
    public boolean update(UserDepartment obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
}

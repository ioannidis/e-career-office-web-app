package com.careeroffice.dao;

import com.careeroffice.database.Queries;
import com.careeroffice.database.QueryCallback;
import com.careeroffice.database.QueryParamCallback;
import com.careeroffice.model.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements CrudDao<Department, String> {
    @Override
    public Department findOne(String id) {

        return Queries.execute("SELECT * FROM departments WHERE id=?", new QueryParamCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, id);
            }

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new Department(
                            resultSet.getString("id"),
                            resultSet.getString("title")
                    );
                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public List<Department> findAll() {

        return Queries.execute("SELECT * FROM departments", new QueryCallback() {
            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                List<Department> departments = new ArrayList<>();

                while (resultSet.next()) {
                    departments.add(new Department(
                            resultSet.getString("id"),
                            resultSet.getString("title")
                    ));
                }

                return departments;
            }
        });
    }

    @Override
    public boolean save(Department obj) {
        return false;
    }

    @Override
    public boolean update(Department obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public int count() {

        return Queries.execute("SELECT COUNT(*) FROM departments", new QueryCallback() {
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

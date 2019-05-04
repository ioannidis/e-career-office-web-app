package com.careeroffice.dao;

import com.careeroffice.database.Callback;
import com.careeroffice.database.Queries;
import com.careeroffice.database.ResultCallback;
import com.careeroffice.database.UpdateCallback;
import com.careeroffice.model.UserDepartment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDepartmentDao implements CrudDao<UserDepartment, String> {

    private static final String Find_One_Query = "SELECT * FROM user_department WHERE username=?";
    private static final String Find_All_Query = "SELECT * FROM user_department";
    private static final String Save_Query = "INSERT INTO user_department(username, department_id) VALUES (?, ?)";
    private static final String Update_Query = "UPDATE user_department SET department_id=? WHERE username=?";
    private static final String Delete_Query = "DELETE FROM user_department WHERE username=?";
    private static final String Count_Query = "SELECT COUNT(*) FROM user_department";

    @Override
    public UserDepartment findOne(String id) {

        return Queries.execute(Find_One_Query, new ResultCallback() {
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

        return Queries.execute(Find_All_Query, new Callback() {
            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                List<UserDepartment> items = new ArrayList<>();

                while (resultSet.next()) {
                    items.add(new UserDepartment(
                            resultSet.getString("username"),
                            resultSet.getString("department_id")
                    ));
                }

                return items;
            }
        });
    }

    @Override
    public boolean save(UserDepartment obj) {

        return Queries.executeUpdate(Save_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getUsername());
                statement.setString(2, obj.getDepartmentId());
            }
        });
    }

    @Override
    public boolean update(UserDepartment obj) {

        return Queries.executeUpdate(Update_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getDepartmentId());
                statement.setString(2, obj.getUsername());
            }
        });
    }

    @Override
    public boolean delete(String id) {

        return Queries.executeUpdate(Delete_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, id);
            }
        });
    }

    @Override
    public int count() {

        return Queries.execute(Count_Query, new Callback() {
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

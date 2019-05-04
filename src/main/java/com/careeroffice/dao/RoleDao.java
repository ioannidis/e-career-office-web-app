package com.careeroffice.dao;

import com.careeroffice.database.Callback;
import com.careeroffice.database.Queries;
import com.careeroffice.database.ResultCallback;
import com.careeroffice.database.UpdateCallback;
import com.careeroffice.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao implements CrudDao<Role, String> {

    private static final String Find_One_Query = "SELECT * FROM roles WHERE id=?";
    private static final String Find_All_Query = "SELECT * FROM roles";
    private static final String Save_Query = "INSERT INTO roles(id, title) VALUES (?, ?)";
    private static final String Update_Query = "UPDATE roles SET title=? WHERE id=?";
    private static final String Delete_Query = "DELETE FROM roles WHERE id=?";
    private static final String Count_Query = "SELECT COUNT(*) FROM roles";

    @Override
    public Role findOne(String id) {

        return Queries.execute(Find_One_Query, new ResultCallback() {
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

        return Queries.execute(Find_All_Query, new Callback() {
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
    public Role save(Role obj) {

        Queries.executeUpdate(Save_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getId());
                statement.setString(2, obj.getTitle());
            }
        });

        return obj;
    }

    @Override
    public Role update(Role obj) {

        Queries.executeUpdate(Update_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getTitle());
                statement.setString(2, obj.getId());
            }
        });

        return obj;
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

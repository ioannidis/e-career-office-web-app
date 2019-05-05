package com.careeroffice.dao;

import com.careeroffice.database.*;
import com.careeroffice.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements CrudDao<User, String> {

    private static final String Find_One_Query = "SELECT * FROM users WHERE username=?";
    private static final String Find_All_Query = "SELECT * FROM users";
    private static final String Save_Query = "INSERT INTO users(username, password, first_name, last_name, phone_number, email, role_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String Update_Query = "UPDATE users SET password=?, first_name=?, last_name=?, phone_number=?, email=? WHERE username=?";
    private static final String Delete_Query = "DELETE FROM users WHERE username=?";
    private static final String Count_Query = "SELECT COUNT(*) FROM users";

    @Override
    public User findOne(String id) {

        return Queries.execute(Find_One_Query, new ResultCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, id);
            }

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("email"),
                            resultSet.getString("role_id")
                    );
                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public List<User> findAll() {

        return Queries.execute(Find_All_Query, new Callback() {

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                List<User> users = new ArrayList<>();

                while (resultSet.next()) {
                    users.add(new User(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("email"),
                            resultSet.getString("role_id")
                    ));
                }

                return users;
            }
        });
    }

    public List<User> findStudents() {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT * FROM users WHERE role_id = 'u_student' OR role_id = 'p_student'";

        try {
            con = DatabaseConnection.getConnection();
            stmt = con.prepareStatement(str);
            rs = stmt.executeQuery();

            List<User> users = new ArrayList<>();

            while (rs.next()) {
                users.add(new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("role_id")
                ));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }



    @Override
    public User save(User obj) {

        Queries.executeUpdate(Save_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getUsername());
                statement.setString(2, obj.getPassword());
                statement.setString(3, obj.getName());
                statement.setString(4, obj.getSurname());
                statement.setString(5, obj.getPhoneNumber());
                statement.setString(6, obj.getEmail());
                statement.setString(7, obj.getRoleId());
            }
        });

        return obj;
    }

    @Override
    public User update(User obj) {

        Queries.executeUpdate(Update_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getPassword());
                statement.setString(2, obj.getName());
                statement.setString(3, obj.getSurname());
                statement.setString(4, obj.getPhoneNumber());
                statement.setString(5, obj.getEmail());
                statement.setString(6, obj.getUsername());
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

    public boolean searchUserBy(String value, String field) {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT * FROM users WHERE " + field + " =?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, value);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}

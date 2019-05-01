package com.careeroffice.dao;

import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements CrudDao<User, String> {

    @Override
    public User findOne(String id) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT users.username, password, first_name, last_name, " +
                "phone_number, email, role_id, uc.company_id, ud.department_id " +
                "FROM users " +
                "LEFT JOIN user_company uc ON users.username = uc.username " +
                "LEFT JOIN user_department ud on users.username = ud.username " +
                "WHERE users.username = ?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("role_id"),
                        rs.getString("company_id"),
                        rs.getString("department_id")
                );
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

        return null;
    }

    @Override
    public List<User> findAll() {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT users.username, password, first_name, last_name, " +
                "phone_number, email, role_id, uc.company_id, ud.department_id " +
                "FROM users " +
                "LEFT JOIN user_company uc ON users.username = uc.username " +
                "LEFT JOIN user_department ud on users.username = ud.username";

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
                        rs.getString("role_id"),
                        rs.getString("company_id"),
                        rs.getString("department_id")
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

    public List<User> findStudents() {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT users.username, password, first_name, last_name, " +
                "phone_number, email, role_id, uc.company_id, ud.department_id " +
                "FROM users " +
                "LEFT JOIN user_company uc ON users.username = uc.username " +
                "LEFT JOIN user_department ud on users.username = ud.username "+
                "WHERE users.role_id = 'u_student' OR users.role_id = 'p_student' ";

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
                        rs.getString("role_id"),
                        rs.getString("company_id"),
                        rs.getString("department_id")
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
    public boolean save(User obj) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "insert into users" +
                "(username, password, first_name, last_name, phone_number, email, role_id)" +
                " values (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, obj.getUsername());
            stmt.setString(2, obj.getPassword());
            stmt.setString(3, obj.getName());
            stmt.setString(4, obj.getSurname());
            stmt.setString(5, obj.getPhoneNumber());
            stmt.setString(6, obj.getEmail());
            stmt.setString(7, obj.getRoleId());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean update(User obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public int count() {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT COUNT(*) FROM users";

        try {
            con = DatabaseConnection.getConnection();
            stmt = con.prepareStatement(str);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
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

        return 0;
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

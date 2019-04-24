package com.careeroffice.service;

import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Handles login validity and attempts.
 */
public class UserService implements IPersistenceService<User> {

//    /**
//     * An instance of the database connection.
//     */
//    private DataSource ds;
//
//    /**
//     * Initializes login service.
//     *
//     * @param ds The data source instance.
//     */
//    public UserService( DataSource ds) {
//        this.ds = ds;
//    }

    /**
     * Authenticates a user with the provided username.
     *
     * @param username The username of the user.
     * @return The user object or null.
     */
    public User findOne(String username) {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT * FROM users WHERE username=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getLong("phone_number"),
                        rs.getString("email"),
                        rs.getString("role_id"));
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

    public boolean save(String username, String password, String firstName, String lastName, String phone, String email, String role) {
        Connection con = null;
        PreparedStatement stmt = null;
        
        String str = "insert into users(username, password, first_name, last_name, phone_number, email, role_id) values (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, phone);
            stmt.setString(6, email);
            stmt.setString(7, role);

            stmt.executeUpdate();

            return true;

        } catch ( SQLException e) {
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

    public int count() {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "select count(*) as total from users";
        int total = 0;

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);

            rs = stmt.executeQuery();

            while(rs.next())
                total = rs.getInt("count");

            return total;

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

    @Override
    public boolean delete() {
        return true;
    }

    @Override
    public User findOne() {
        return null;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
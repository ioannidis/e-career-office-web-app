package com.careeroffice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.User;

/**
 * Handles login validity and attempts.
 */
public class LoginService implements IPersistenceService<User> {

    /**
     * An instance of the database connection.
     */
//    private DataSource ds;

    /**
     * Initializes login service.
     *
     * @param ds The data source instance.
     */
//    public LoginService(DataSource ds) {
//        this.ds = ds;
//    }
    public LoginService() {}

    /**
     * Authenticates a user with the provided username.
     *
     * @param username The username of the user.
     * @return The user object or null.
     */
    public User auth(String username) {

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

    @Override
    public User findOne() {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
}
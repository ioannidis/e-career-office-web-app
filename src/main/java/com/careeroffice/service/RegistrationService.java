package com.careeroffice.service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationService {

    /**
     * An instance of the database connection.
     */
    private DataSource ds;

    /**
     * Initializes login service.
     *
     * @param ds The data source instance.
     */
    public RegistrationService(DataSource ds) {
        this.ds = ds;
    }

    public void createUser(String username, String password, String firstName, String lastName, String phone, String email, String role) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "insert into users(username, password, first_name, last_name, phone_number, email, role_id) values (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = ds.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, phone);
            stmt.setString(6, email);
            stmt.setString(7, role);

            stmt.executeUpdate();

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
    }

    public boolean findUserBy(String value, String field) {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT * FROM users WHERE " + field + " =?";

        try {
            con = ds.getConnection();

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

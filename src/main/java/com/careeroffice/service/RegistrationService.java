package com.careeroffice.service;

import com.careeroffice.model.User;

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

        System.out.println( username + " "+ password+ " "+ firstName+ " "+ lastName+ " "+ email+ " "+ phone+ " "+ role );

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
}

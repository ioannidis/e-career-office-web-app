package com.careeroffice.database;

import java.sql.*;

// Singleton pattern
public class DatabaseConnection {

    private static DatabaseConnection db;

    private static Connection connection;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        if (db == null) {
            db = new DatabaseConnection();
        }

        return db.establishConnection();
    }

    private Connection establishConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/career_office","root", "123456");
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        return connection;
    }


}

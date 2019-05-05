package com.careeroffice.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton pattern
public class DatabaseConnection {

    private static DatabaseConnection db;
    private static Connection connection;

    private static final String who = "dionisis";
    private static String URL = "";
    private static String User = "";
    private static String Password = "";

    private static final String Thanos_URL = "jdbc:mysql://localhost:3306/career_office?serverTimezone=Europe/Athens&useSSL=false";
    private static final String Thanos_User = "root";
    private static final String Thanos_Password = "password";

    private static final String Panos_URL = "jdbc:mysql://localhost:3306/career_office";
    private static final String Panos_User = "root";
    private static final String Panos_Password = "123456";

    private static final String Dionisis_URL = "jdbc:mysql://localhost:3306/career_office?serverTimezone=Europe/Athens&useSSL=false";
    private static final String Dionisis_User = "root";
    private static final String Dionisis_Password = "123456";

    private static final String George_URL = "jdbc:mysql://localhost:3306/career_office";
    private static final String George_User = "root";
    private static final String George_Password = "123456";

    private DatabaseConnection() {

        if (who.equals("thanos")) {
            URL = Thanos_URL;
            User = Thanos_User;
            Password = Thanos_Password;
        } else if (who.equals("panos")) {
            URL = Panos_URL;
            User = Panos_User;
            Password = Panos_Password;
        } else if (who.equals("dionisis")) {
            URL = Dionisis_URL;
            User = Dionisis_User;
            Password = Dionisis_Password;
        } else if (who.equals("george")) {
            URL = George_URL;
            User = George_User;
            Password = George_Password;
        }
    }

    public static Connection getConnection() {
        if (db == null) {
            db = new DatabaseConnection();
        }

        return db.establishConnection();
    }

    private Connection establishConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, User, Password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


}

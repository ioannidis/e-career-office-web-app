package com.careeroffice.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Queries {

    public static <T> T execute(String query, Callback callback) {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            con = DatabaseConnection.getConnection();
            stmt = con.prepareStatement(query);

            if (callback instanceof ResultCallback) {
                ResultCallback paramCallback = (ResultCallback) callback;
                paramCallback.setParameters(stmt);
            }

            rs = stmt.executeQuery();

            return (T) callback.fetch(rs);
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

    public static boolean executeUpdate(String query, UpdateCallback callback) {

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DatabaseConnection.getConnection();
            stmt = con.prepareStatement(query);

            callback.setParameters(stmt);

            return stmt.executeUpdate() > 0;
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
}

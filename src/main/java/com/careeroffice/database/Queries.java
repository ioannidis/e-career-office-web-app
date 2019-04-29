package com.careeroffice.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Queries {
    public static Object execute(String query, QueryCallback callback) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(query);

            if (callback instanceof QueryParamCallback) {
                QueryParamCallback paramCallback = (QueryParamCallback) callback;
                paramCallback.setParameters(stmt);
            }

            rs = stmt.executeQuery();

            return callback.fetch(rs);
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
}

package com.careeroffice.dao;

import com.careeroffice.dao.CrudDao;
import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.Cv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CvDao implements CrudDao<Cv, String> {

    @Override
    public Cv findOne(String username) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT * FROM cvs WHERE username=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cv(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("file_url"));
            }

        } catch ( SQLException e) {
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
    public List<Cv> findAll() {
        return null;
    }

    @Override
    public Cv save(Cv obj) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "INSERT INTO cvs (username, file_url) VALUES (?, ?)";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, obj.getUsername());
            stmt.setString(2, obj.getFileUrl());

            stmt.executeUpdate();

            return obj;

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

        return null;
    }

    @Override
    public Cv update(Cv obj) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "UPDATE cvs SET file_url = ? WHERE username = ?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, obj.getFileUrl());
            stmt.setString(2, obj.getUsername());

            stmt.executeUpdate();

            return obj;

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

        return null;
    }

    @Override
    public boolean delete(String id) {

        Connection con = null;
        PreparedStatement stmt = null;

        String str = "DELETE FROM cvs WHERE cvs.username=? ";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, id);

            stmt.executeUpdate();

            return true;

        } catch ( SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public int count() {
        return 0;
    }


}
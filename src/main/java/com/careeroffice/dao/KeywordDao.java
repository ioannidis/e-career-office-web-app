package com.careeroffice.dao;

import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.Keyword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KeywordDao implements CrudDao<Keyword, Integer> {

    @Override
    public Keyword findOne( Integer id ) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT k.id, k.title, k.slug FROM keywords AS k WHERE k.id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if  (rs.next()) {
                return new Keyword(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("slug"));
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
    public List<Keyword> findAll() {
        List<Keyword> keywords = new ArrayList<>();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str =
                "SELECT k.id, k.title, k.slug FROM keywords AS k ";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);


            rs = stmt.executeQuery();

            while (rs.next()) {
                keywords.add(new Keyword(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("slug")));
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

        return keywords;
    }

    @Override
    public Keyword save( Keyword obj ) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str =
                "INSERT INTO keywords (title, slug) VALUES (?, ?)";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getTitle());
            stmt.setString(2, obj.getSlug());

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();

            if (rs.next()){
                obj.setId(rs.getInt(1));
            }

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
    public Keyword update( Keyword obj ) {
        return null;
    }

    @Override
    public boolean delete( Integer id ) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }

    public Keyword findKeywordByTitle(String title) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT k.id, k.title, k.slug FROM keywords AS k WHERE k.title=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, title);

            rs = stmt.executeQuery();

            if  (rs.next()) {
                return new Keyword(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("slug"));
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
}

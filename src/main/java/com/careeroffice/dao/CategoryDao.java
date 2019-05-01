package com.careeroffice.dao;

import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.Category;
import com.careeroffice.model.Classified;
import com.careeroffice.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements CrudDao<Category, Integer> {

    @Override
    public Category findOne( Integer id ) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT * FROM categories WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Category(
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
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT c.id, c.title, c.slug FROM categories AS c ";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);

            rs = stmt.executeQuery();

            while (rs.next()) {
                categories.add(new Category(
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

        return categories;
    }

    @Override
    public boolean save( Category obj ) {
        return false;
    }

    @Override
    public boolean update( Category obj ) {
        return false;
    }

    @Override
    public boolean delete( Integer id ) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
}

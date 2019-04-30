package com.careeroffice.dao;

import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.Classified;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassifiedDao implements CrudDao <Classified, Integer> {

    @Override
    public Classified findOne( Integer id ) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT cl.id, cl.title, cl.content, cl.company_id, cl.category_id  FROM classifieds AS cl WHERE cl.id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if  (rs.next()) {
                return new Classified(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("company_id"),
                        rs.getInt("category_id"));
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
    public List<Classified> findAll() {
        return null;
    }

    @Override
    public boolean save( Classified obj ) {

        Connection con = null;
        PreparedStatement stmt = null;

        String str = "INSERT INTO classifieds(title,content,company_id,category_id) VALUES (?,?,?,?)";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, obj.getTitle());
            stmt.setString(2, obj.getContent());
            stmt.setString(3, obj.getCompanyId());
            stmt.setInt(4, obj.getCategoryId());

            stmt.executeUpdate();

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

        return true;
    }

    @Override
    public boolean update( Classified obj ) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "UPDATE classifieds SET title=?, content=?, company_id=?, category_id=? WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, obj.getTitle());
            stmt.setString(2, obj.getContent());
            stmt.setString(3, obj.getCompanyId());
            stmt.setInt(4, obj.getCategoryId());
            stmt.setInt(5, obj.getId());

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
    public boolean delete( Integer id ) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "DELETE FROM classifieds WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setInt(1, id);

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

    public List<Classified> findAllByCompany( String companyId) {
        List<Classified> classifieds = new ArrayList<>();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str =
                "SELECT cl.id, cl.title, cl.content, cl.company_id, cl.category_id "
                        + "FROM classifieds AS cl "
                        + "INNER JOIN companies AS co "
                        + "ON cl.company_id = co.id "
                        + "WHERE cl.company_id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, companyId);

            rs = stmt.executeQuery();

            while (rs.next()) {
                classifieds.add(new Classified(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("company_id"),
                        rs.getInt("category_id")));
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

        return classifieds;
    }
}

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
        return null;
    }

    @Override
    public List<Classified> findAll() {
        return null;
    }

    @Override
    public boolean save( Classified obj ) {
        return false;
    }

    @Override
    public boolean update( Classified obj ) {
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

    public List<Classified> findAllByCompany( String companyId) {
        List<Classified> courses = new ArrayList<>();

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
                courses.add(new Classified(
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

        return courses;
    }
}

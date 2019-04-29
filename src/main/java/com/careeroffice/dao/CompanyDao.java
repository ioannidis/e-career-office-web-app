package com.careeroffice.dao;

import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompanyDao implements CrudDao<Company, String> {

    @Override
    public Company findOne(String id) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT * FROM companies WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Company(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("website")
                );
            }

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

    @Override
    public List<Company> findAll() {
        return null;
    }

    @Override
    public boolean save(Company obj) {
        return false;
    }

    @Override
    public boolean update(Company obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
}

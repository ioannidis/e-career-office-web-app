package com.careeroffice.dao;

import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements CrudDao<Department, String> {
    @Override
    public Department findOne(String id) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT * FROM departments WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Department(
                        rs.getString("id"),
                        rs.getString("title")
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
    public List<Department> findAll() {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT * FROM departments";

        try {
            con = DatabaseConnection.getConnection();
            stmt = con.prepareStatement(str);
            rs = stmt.executeQuery();

            List<Department> departments = new ArrayList<>();

            while (rs.next()) {
                departments.add(new Department(
                        rs.getString("id"),
                        rs.getString("title")
                ));
            }

            return departments;
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
    public boolean save(Department obj) {
        return false;
    }

    @Override
    public boolean update(Department obj) {
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

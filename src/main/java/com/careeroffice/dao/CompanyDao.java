package com.careeroffice.dao;

import com.careeroffice.database.Callback;
import com.careeroffice.database.Queries;
import com.careeroffice.database.ResultCallback;
import com.careeroffice.database.UpdateCallback;
import com.careeroffice.model.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao implements CrudDao<Company, String> {

    private static final String Find_One_Query = "SELECT * FROM companies WHERE id=?";
    private static final String Find_All_Query = "SELECT * FROM companies";
    private static final String Save_Query = "INSERT INTO companies(id, title, address, phone_number, email, website) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String Update_Query = "UPDATE companies SET title=?, address=?, phone_number=?, email=?, website=? WHERE id=?";
    private static final String Delete_Query = "DELETE FROM companies WHERE id=?";
    private static final String Count_Query = "SELECT COUNT(*) FROM companies";

    @Override
    public Company findOne(String id) {

        return Queries.execute(Find_One_Query, new ResultCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, id);
            }

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new Company(
                            resultSet.getString("id"),
                            resultSet.getString("title"),
                            resultSet.getString("address"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("email"),
                            resultSet.getString("website")
                    );
                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public List<Company> findAll() {

        return Queries.execute(Find_All_Query, new Callback() {
            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                List<Company> companies = new ArrayList<>();

                while (resultSet.next()) {
                    companies.add(new Company(
                            resultSet.getString("id"),
                            resultSet.getString("title"),
                            resultSet.getString("address"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("email"),
                            resultSet.getString("website")
                    ));
                }

                return companies;
            }
        });
    }

    @Override
    public boolean save(Company obj) {

        return Queries.executeUpdate(Save_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getId());
                statement.setString(2, obj.getTitle());
                statement.setString(3, obj.getAddress());
                statement.setString(4, obj.getPhoneNumber());
                statement.setString(5, obj.getEmail());
                statement.setString(6, obj.getWebsite());
            }
        });
    }

    @Override
    public boolean update(Company obj) {

        return Queries.executeUpdate(Update_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getTitle());
                statement.setString(2, obj.getAddress());
                statement.setString(3, obj.getPhoneNumber());
                statement.setString(4, obj.getEmail());
                statement.setString(5, obj.getWebsite());
                statement.setString(6, obj.getId());
            }
        });
    }

    @Override
    public boolean delete(String id) {

        return Queries.executeUpdate(Delete_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, id);
            }
        });
    }

    @Override
    public int count() {

        return Queries.execute(Count_Query, new Callback() {
            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
            }
        });
    }
}

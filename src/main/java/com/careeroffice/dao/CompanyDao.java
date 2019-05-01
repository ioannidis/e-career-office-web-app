package com.careeroffice.dao;

import com.careeroffice.database.Queries;
import com.careeroffice.database.QueryCallback;
import com.careeroffice.database.QueryParamCallback;
import com.careeroffice.model.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao implements CrudDao<Company, String> {

    @Override
    public Company findOne(String id) {

        return Queries.execute("SELECT * FROM companies WHERE id=?", new QueryParamCallback() {
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

        return Queries.execute("SELECT * FROM companies", new QueryCallback() {
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

        return Queries.execute("SELECT COUNT(*) FROM companies", new QueryCallback() {
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

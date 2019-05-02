package com.careeroffice.dao;

import com.careeroffice.database.Queries;
import com.careeroffice.database.QueryParamCallback;
import com.careeroffice.model.UserCompany;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserCompanyDao implements CrudDao<UserCompany, String> {

    @Override
    public UserCompany findOne(String id) {
        return Queries.execute("SELECT * FROM user_company WHERE username=?", new QueryParamCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, id);
            }

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new UserCompany(
                            resultSet.getString("username"),
                            resultSet.getString("company_id")
                    );
                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public List<UserCompany> findAll() {
        return null;
    }

    @Override
    public boolean save(UserCompany obj) {
        return false;
    }

    @Override
    public boolean update(UserCompany obj) {
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

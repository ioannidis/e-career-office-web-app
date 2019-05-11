package com.careeroffice.dao.pivot;

import com.careeroffice.dao.CrudDao;
import com.careeroffice.database.Callback;
import com.careeroffice.database.Queries;
import com.careeroffice.database.ResultCallback;
import com.careeroffice.database.UpdateCallback;
import com.careeroffice.model.UserCompany;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserCompanyDao implements CrudDao<UserCompany, String> {

    private static final String Find_One_Query = "SELECT * FROM user_company WHERE username=?";
    private static final String Find_All_Query = "SELECT * FROM user_company";
    private static final String Save_Query = "INSERT INTO user_company(username, company_id) VALUES (?, ?)";
    private static final String Update_Query = "UPDATE user_company SET company_id=? WHERE username=?";
    private static final String Delete_Query = "DELETE FROM user_company WHERE username=?";
    private static final String Count_Query = "SELECT COUNT(*) FROM user_company";

    @Override
    public UserCompany findOne(String id) {

        return Queries.execute(Find_One_Query, new ResultCallback() {
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

        return Queries.execute(Find_All_Query, new Callback() {
            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                List<UserCompany> items = new ArrayList<>();

                while (resultSet.next()) {
                    items.add(new UserCompany(
                            resultSet.getString("username"),
                            resultSet.getString("company_id")
                    ));
                }

                return items;
            }
        });
    }

    @Override
    public UserCompany save( UserCompany obj) {

        Queries.executeUpdate(Save_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getUsername());
                statement.setString(2, obj.getCompanyId());
            }
        });

        return obj;
    }

    @Override
    public UserCompany update(UserCompany obj) {

        Queries.executeUpdate(Update_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getCompanyId());
                statement.setString(2, obj.getUsername());
            }
        });

        return obj;
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

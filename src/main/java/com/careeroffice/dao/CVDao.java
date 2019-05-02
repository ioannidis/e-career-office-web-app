package com.careeroffice.dao;

import com.careeroffice.database.Queries;
import com.careeroffice.database.QueryParamCallback;
import com.careeroffice.model.CV;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CVDao implements CrudDao<CV, Integer> {
    @Override
    public CV findOne(Integer id) {
        return Queries.execute("SELECT * FROM cvs WHERE id=?", new QueryParamCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new CV(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("file_url")
                    );
                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public List<CV> findAll() {
        return null;
    }

    @Override
    public boolean save(CV obj) {
        return false;
    }

    @Override
    public boolean update(CV obj) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
    @Override
    public int count() {
        return 0;
    }
}

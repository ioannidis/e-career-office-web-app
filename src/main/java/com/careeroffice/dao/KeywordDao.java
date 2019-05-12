package com.careeroffice.dao;

import com.careeroffice.database.*;
import com.careeroffice.model.Keyword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KeywordDao implements CrudDao<Keyword, Integer> {

    private static final String Find_One_Query = "SELECT * FROM keywords WHERE id=?";
    private static final String Find_All_Query = "SELECT * FROM keywords";
    private static final String Save_Query = "INSERT INTO keywords(title, slug) VALUES (?, ?)";
    private static final String Update_Query = "UPDATE keywords SET title=?, slug=? WHERE id=?";
    private static final String Delete_Query = "DELETE FROM keywords WHERE id=?";
    private static final String Count_Query = "SELECT COUNT(*) FROM keywords";

    @Override
    public Keyword findOne(Integer id) {

        return Queries.execute(Find_One_Query, new ResultCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new Keyword(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("slug"));

                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public List<Keyword> findAll() {

        return Queries.execute(Find_All_Query, new Callback() {
            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                List<Keyword> keywords = new ArrayList<>();

                while (resultSet.next()) {
                    keywords.add(new Keyword(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("slug")));
                }

                return keywords;
            }
        });
    }

    @Override
    public Keyword save(Keyword obj) {

        int id = Queries.executeUpdateAutoInc(Save_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getTitle());
                statement.setString(2, obj.getSlug());
            }
        });

        obj.setId(id);
        return obj;
    }

    @Override
    public Keyword update(Keyword obj) {

        Queries.executeUpdate(Update_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getTitle());
                statement.setString(2, obj.getSlug());
                statement.setInt(3, obj.getId());
            }
        });

        return obj;
    }

    @Override
    public boolean delete(Integer id) {

        return Queries.executeUpdate(Delete_Query, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
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

    public Keyword findKeywordByTitle(String title) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT k.id, k.title, k.slug FROM keywords AS k WHERE k.title=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setString(1, title);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Keyword(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("slug"));
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
}

package com.careeroffice.dao;

import com.careeroffice.database.Callback;
import com.careeroffice.database.Queries;
import com.careeroffice.database.ResultCallback;
import com.careeroffice.database.UpdateCallback;
import com.careeroffice.model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements CrudDao<Category, Integer> {

    private static final String Find_One_Query = "SELECT * FROM categories WHERE id=?";
    private static final String Find_All_Query = "SELECT * FROM categories";
    private static final String Save_Query = "INSERT INTO categories(title, slug) VALUES (?, ?)";
    private static final String Update_Query = "UPDATE categories SET title=?, slug=? WHERE id=?";
    private static final String Delete_Query = "DELETE FROM categories WHERE id=?";
    private static final String Count_Query = "SELECT COUNT(*) FROM categories";

    @Override
    public Category findOne(Integer id) {

        return Queries.execute(Find_One_Query, new ResultCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new Category(
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
    public List<Category> findAll() {

        return Queries.execute(Find_All_Query, new Callback() {
            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                List<Category> items = new ArrayList<>();

                while (resultSet.next()) {
                    items.add(new Category(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("slug")));
                }

                return items;
            }
        });
    }

    @Override
    public Category save(Category obj) {

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
    public Category update(Category obj) {

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
}

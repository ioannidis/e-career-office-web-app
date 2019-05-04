package com.careeroffice.dao;

import com.careeroffice.database.*;
import com.careeroffice.model.Classified;
import com.careeroffice.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassifiedDao implements CrudDao <Classified, Integer> {

    @Override
    public Classified findOne( Integer id ) {

        final String str = "SELECT cl.id, cl.title, cl.content, cl.company_id, cl.category_id  FROM classifieds AS cl WHERE cl.id=?";

        return Queries.execute(str, new ResultCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                if  (resultSet.next()) {
                    return new Classified(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("content"),
                            resultSet.getString("company_id"),
                            resultSet.getInt("category_id"));
                } else {
                    return null;
                }
            }
        });

    }

    @Override
    public List<Classified> findAll() {

        final String str =
                "SELECT cl.id, cl.title, cl.content, cl.company_id, cl.category_id "
                        + "FROM classifieds AS cl "
                        + "INNER JOIN companies AS co "
                        + "ON cl.company_id = co.id ";

        return Queries.execute(str, new Callback() {

            @Override
            public Object fetch(ResultSet resultSet) throws SQLException {
                List<Classified> classifieds = new ArrayList<>();

                while (resultSet.next()) {
                    classifieds.add(new Classified(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("content"),
                            resultSet.getString("company_id"),
                            resultSet.getInt("category_id")));
                }

                return classifieds;
            }
        });
    }


    public String findClassifiedSkills(int id,String type) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT cl.id, cl.title, cl.content, cl.company_id, cl.category_id, kw.title AS skills,kw.slug AS slug\n" +
                "    FROM classifieds AS cl ,\n" +
                "    keyword_classified AS kc,\n" +
                "    keywords AS kw,\n" +
                "    companies as co\n" +
                "    WHERE cl.company_id = co.id\n" +
                "    AND kc.classified_id = cl.id\n" +
                "    AND cl.id= ?\n" +
                "    AND kc.keyword_id = kw.id;";

        try {
            con = DatabaseConnection.getConnection();
            stmt = con.prepareStatement(str);
            stmt.setString(1,String.valueOf(id));
            rs = stmt.executeQuery();

            StringBuilder skills = new StringBuilder();
            skills.append("None");
            StringBuilder slug = new StringBuilder();
            slug.append("None");

            if (rs.next()){
                skills = new StringBuilder();
                skills.append(rs.getString("skills"));
                slug = new StringBuilder();
                slug.append(rs.getString("slug"));
            }

            while (rs.next()) {
                skills.append(", "+rs.getString("skills"));
                slug.append(", "+rs.getString("slug"));
            }
            if (type.equals("slug")){
                return slug.toString();
            }
            return skills.toString();
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
    public Classified save( Classified obj ) {

        final String str = "INSERT INTO classifieds(title,content,company_id,category_id) VALUES (?,?,?,?)";

        int id = Queries.executeUpdateAutoInc(str, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getTitle());
                statement.setString(2, obj.getContent());
                statement.setString(3, obj.getCompanyId());
                statement.setInt(4, obj.getCategoryId());
            }
        });

        obj.setId(id);
        return obj;
    }

    @Override
    public Classified update( Classified obj ) {

        final String str = "UPDATE classifieds SET title=?, content=?, company_id=?, category_id=? WHERE id=?";

        Queries.executeUpdate(str, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, obj.getTitle());
                statement.setString(2, obj.getContent());
                statement.setString(3, obj.getCompanyId());
                statement.setInt(4, obj.getCategoryId());
                statement.setInt(5, obj.getId());
            }
        });

        return obj;
    }

    @Override
    public boolean delete( Integer id ) {

        String str = "DELETE FROM classifieds WHERE id=?";

        return Queries.executeUpdate(str, new UpdateCallback() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }
        });
    }

    @Override
    public int count() {
        return 0;
    }

    public List<Classified> findAllByCompany( String companyId) {
        List<Classified> classifieds = new ArrayList<>();

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
                classifieds.add(new Classified(
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

        return classifieds;
    }
}

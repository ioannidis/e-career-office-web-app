package com.careeroffice.dao.student;

import com.careeroffice.dao.CrudDao;
import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.KeywordCv;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KeywordCvDao implements CrudDao<KeywordCv, Integer> {
    @Override
    public KeywordCv findOne( Integer id ) {
        return null;
    }

    @Override
    public List<KeywordCv> findAll() {
        return null;
    }

    @Override
    public KeywordCv save( KeywordCv obj ) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "INSERT INTO keyword_cv VALUES (?,?)";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setInt(1, obj.getKeywordId());
            stmt.setInt(2, obj.getCvId());

            stmt.executeUpdate();

        } catch ( SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return obj;
    }

    @Override
    public KeywordCv update( KeywordCv obj ) {
        return null;
    }

    @Override
    public boolean delete( Integer id ) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "DELETE FROM keyword_cv WHERE cv_id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setInt(1, id);

            stmt.executeUpdate();

            return true;

        } catch ( SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean delete( KeywordCv obj ) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "DELETE FROM keyword_cv WHERE keyword_id=? AND cv_id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setInt(1, obj.getKeywordId());
            stmt.setInt(1, obj.getCvId());

            stmt.executeUpdate();

            return true;

        } catch ( SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public int count() {
        return 0;
    }

    public List<KeywordCv> findByCv(int id) {
        List<KeywordCv> keywordCvs = new ArrayList<>();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT k.keyword_id, k.cv_id FROM keyword_cv AS k WHERE k.cv_id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while  (rs.next()) {
                keywordCvs.add(new KeywordCv(
                        rs.getInt("keyword_id"),
                        rs.getInt("cv_id")));
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

        return keywordCvs;
    }


}

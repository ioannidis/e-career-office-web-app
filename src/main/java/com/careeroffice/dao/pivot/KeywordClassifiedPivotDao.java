package com.careeroffice.dao.pivot;

import com.careeroffice.dao.CrudDao;
import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.KeywordClassifiedPivot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KeywordClassifiedPivotDao implements CrudDao<KeywordClassifiedPivot, Integer> {
    @Override
    public KeywordClassifiedPivot findOne( Integer id ) {
        return null;
    }

    @Override
    public List<KeywordClassifiedPivot> findAll() {
        return null;
    }

    @Override
    public KeywordClassifiedPivot save( KeywordClassifiedPivot obj ) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "INSERT INTO keyword_classified VALUES (?,?)";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setInt(1, obj.getKeywordId());
            stmt.setInt(2, obj.getClassifiedId());

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
    public KeywordClassifiedPivot update( KeywordClassifiedPivot obj ) {
        return null;
    }

    @Override
    public boolean delete( Integer id ) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "DELETE FROM keyword_classified WHERE classified_id=?";

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

    public boolean delete( KeywordClassifiedPivot obj ) {
        Connection con = null;
        PreparedStatement stmt = null;

        String str = "DELETE FROM keyword_classified WHERE keyword_id=? AND classified_id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setInt(1, obj.getKeywordId());
            stmt.setInt(1, obj.getClassifiedId());

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

    public List<KeywordClassifiedPivot> findByClassified(int id) {
        List<KeywordClassifiedPivot> keywordClassifiedPivots = new ArrayList<>();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String str = "SELECT k.keyword_id, k.classified_id FROM keyword_classified AS k WHERE k.classified_id=?";

        try {
            con = DatabaseConnection.getConnection();

            stmt = con.prepareStatement(str);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while  (rs.next()) {
                keywordClassifiedPivots.add(new KeywordClassifiedPivot(
                        rs.getInt("keyword_id"),
                        rs.getInt("classified_id")));
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

        return keywordClassifiedPivots;
    }
}

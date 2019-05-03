package com.careeroffice.dao;

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
    public boolean save( KeywordClassifiedPivot obj ) {
        return false;
    }

    @Override
    public boolean update( KeywordClassifiedPivot obj ) {
        return false;
    }

    @Override
    public boolean delete( Integer id ) {
        return false;
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

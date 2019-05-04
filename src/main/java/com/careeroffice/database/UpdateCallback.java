package com.careeroffice.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface UpdateCallback {

    void setParameters(PreparedStatement statement) throws SQLException;
}

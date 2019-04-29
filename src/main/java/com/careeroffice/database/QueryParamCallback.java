package com.careeroffice.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryParamCallback extends QueryCallback {

    void setParameters(PreparedStatement statement) throws SQLException;
}

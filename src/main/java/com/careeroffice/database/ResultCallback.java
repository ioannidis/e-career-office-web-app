package com.careeroffice.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ResultCallback extends Callback {

    void setParameters(PreparedStatement statement) throws SQLException;
}

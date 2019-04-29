package com.careeroffice.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Callback {

    Object fetch(ResultSet resultSet) throws SQLException;
}

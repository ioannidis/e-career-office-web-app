package com.careeroffice.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryCallback {

    Object fetch(ResultSet resultSet) throws SQLException;
}

package com.dusancoko.dbmanager;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDBManager extends AbstractDBManager {

    private SQLiteDBManager(String pathToDatabase) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        dbConnection = DriverManager.getConnection("jdbc:sqlite:" + pathToDatabase);
    }

    public static void init(String pathToDatabase) throws SQLException, ClassNotFoundException {
        if(AbstractDBManager.instance == null)
            AbstractDBManager.instance = new SQLiteDBManager(pathToDatabase);
    }


}

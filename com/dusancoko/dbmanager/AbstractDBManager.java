package com.dusancoko.dbmanager;

import java.sql.Connection;

public abstract class AbstractDBManager {
    protected static AbstractDBManager instance;
    protected static Connection dbConnection;

    public static Connection open() { return dbConnection; }
}

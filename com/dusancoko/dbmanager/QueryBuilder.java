package com.dusancoko.dbmanager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.StringJoiner;

public class QueryBuilder {
    private Connection connection;
    private HashMap<Integer, String> query;
    private int order;

    public QueryBuilder() {
        this.connection = AbstractDBManager.open();
        query = new HashMap<>(); order = 0;
    }

    /************************
      SELECT Query methods
    ************************/

    public QueryBuilder select() {
        return select(null);
    }

    public QueryBuilder select(String[] fields) {
        query.put(order++,"SELECT");
        if(fields == null) query.put(order++,"*");
        else {
            StringJoiner stringJoiner = new StringJoiner(", ");
            for(String field : fields) stringJoiner.add(field);
            query.put(order++,stringJoiner.toString());
        }
        return this;
    }

    public QueryBuilder from(String tableName) {
        query.put(order++,"FROM " + tableName);
        return this;
    }

    public QueryBuilder from(String[] tables) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (String table : tables) stringJoiner.add(table);
        query.put(order++,"FROM " + stringJoiner.toString());
        return this;
    }

    /************************
     WHERE Clauses
     ************************/

    public QueryBuilder where(Condition condition) {
            query.put(order++, "WHERE");
            query.put(order++,condition.toString());
        return this;
    }

    public QueryBuilder andWhere(Condition condition) {
        query.put(order++, "AND");
        query.put(order++,condition.toString());
        return this;
    }

    public QueryBuilder orWhere(Condition condition) {
        query.put(order++, "OR");
        query.put(order++,condition.toString());
        return this;
    }


    /************************
     EXECUTE methods
     ************************/
    private String joinQuery() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        query.forEach((k,v) -> {
            stringJoiner.add(v);
        });
        return stringJoiner.toString();
    }

    public ResultSet execute() throws SQLException {
        return query(joinQuery());
    }

    public int update() throws SQLException {
        return update(joinQuery());
    }

    public int update(String sqlQuery) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sqlQuery);
    }


    public ResultSet query(String sqlQuery) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sqlQuery);
    }
}

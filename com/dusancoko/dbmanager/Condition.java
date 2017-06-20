package com.dusancoko.dbmanager;



public class Condition {
    private String key;
    private Operator operator;
    private Object value;

    public Condition(String key, Operator operator, Object value) {
        this.key = key;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + operator + (value.getClass() == String.class ? "'" + value + "'" : value );
    }
}

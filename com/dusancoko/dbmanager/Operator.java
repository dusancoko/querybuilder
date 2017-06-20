package com.dusancoko.dbmanager;

public enum Operator {
    EQUALS("="), LESS("<"), LESSTHAN("<="), GREATER(">"), GREATERTHAN(">="), NOT("NOT"), DIFFERS("<>"), IN("IN"), NOTIN("NOT IN");
    private String operator;

    private Operator(String operator) {
        this.operator = operator;
    }

    public String toString() { return operator; }
}

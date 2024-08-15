package org.faya.sensei.behavioral.interpreter;

public final class SQLContext {

    private final SQLDatabase database = SQLDatabase.getInstance();
    private final String query;

    private SQLTable cursor;

    public SQLContext(String query) {
        this.query = query.trim();
    }

    public String getQuery() {
        return query;
    }

    public SQLDatabase getDatabase() {
        return database;
    }

    public SQLTable getCursor() {
        return cursor;
    }

    public void setCursor(SQLTable cursor) {
        this.cursor = cursor;
    }
}

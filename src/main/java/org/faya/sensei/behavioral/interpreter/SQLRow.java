package org.faya.sensei.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SQLRow {

    private final Map<String, Comparable<?>> columnValues;

    public SQLRow() {
        this.columnValues = new HashMap<>();
    }

    public SQLRow(Map<String, Comparable<?>> columnValues) {
        this.columnValues = columnValues;
    }

    public void addColumnValue(String column, Comparable<?> value) {
        columnValues.put(column.toLowerCase(), value);
    }

    public Comparable<?> getColumnValue(String column) {
        return columnValues.get(column.toLowerCase());
    }

    public Set<String> getColumns() {
        return columnValues.keySet();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SQLRow sqlRow = (SQLRow) obj;
        return Objects.equals(columnValues, sqlRow.columnValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnValues);
    }
}
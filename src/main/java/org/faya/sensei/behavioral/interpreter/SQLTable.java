package org.faya.sensei.behavioral.interpreter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SQLTable {

    private final String name;
    private final List<SQLRow> rows;
    private final Set<String> columns;

    public SQLTable(String name, Set<String> columns) {
        this.name = name.toLowerCase();
        this.rows = new ArrayList<>();
        this.columns = new HashSet<>(columns);
    }

    public SQLTable(String name, Set<String> columns, List<SQLRow> rows) {
        this.name = name.toLowerCase();
        this.rows = new ArrayList<>(rows);
        this.columns = new HashSet<>(columns);
    }

    public String getName() {
        return name;
    }

    public List<SQLRow> getRows() {
        return rows;
    }

    public Set<String> getColumns() {
        return columns;
    }

    public SQLTable select(final Set<String> selectedColumns) {
        final List<SQLRow> selectedRows = rows.stream()
                .map(row -> {
                    final SQLRow selectedRow = new SQLRow();

                    selectedColumns.stream()
                            .filter(columns::contains)
                            .forEach(col -> selectedRow.addColumnValue(col, row.getColumnValue(col)));

                    return selectedRow;
                })
                .toList();

        return new SQLTable(name + "_selected", selectedColumns, selectedRows);
    }

    public SQLTable combine(final SQLTable rhs) {
        if (!columns.equals(rhs.getColumns()))
            throw new IllegalArgumentException("Cannot combine tables with different columns");

        final List<SQLRow> combinedRows = new ArrayList<>(rows);
        combinedRows.addAll(rhs.rows);

        return new SQLTable(name + "_combined", columns, combinedRows);
    }

    public SQLTable filter(final String column, final String operator, final Comparable<?> value) {
        final List<SQLRow> filteredRows = rows.stream()
                .filter(row -> {
                    final Comparable<?> columnValue = row.getColumnValue(column);
                    if (columnValue == null) return false;

                    return switch (operator) {
                        case "=" -> columnValue.equals(value);
                        case ">" -> ((Comparable<Object>) columnValue).compareTo(value) > 0;
                        case "<" -> ((Comparable<Object>) columnValue).compareTo(value) < 0;
                        default -> throw new UnsupportedOperationException("Unsupported operator: " + operator);
                    };
                })
                .toList();

        return new SQLTable(name + "_filtered", columns, filteredRows);
    }
}
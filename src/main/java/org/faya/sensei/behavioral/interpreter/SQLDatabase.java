package org.faya.sensei.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SQLDatabase {

    private static SQLDatabase instance;

    private final Map<String, SQLTable> tables = new HashMap<>();

    public static synchronized SQLDatabase getInstance() {
        if (instance == null) instance = new SQLDatabase();
        return instance;
    }

    public void addTable(String tableName, SQLTable table) {
        tables.put(tableName.toLowerCase(), table);
    }

    public SQLTable getTable(String tableName) {
        return tables.get(tableName.toLowerCase());
    }

    public Set<String> getTableNames() {
        return tables.keySet();
    }
}

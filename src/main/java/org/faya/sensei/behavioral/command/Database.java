package org.faya.sensei.behavioral.command;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private final List<String> rows = new ArrayList<>();

    public List<String> getRows() {
        return rows;
    }

    public String getRow(int index) {
        return rows.get(index);
    }

    public int insert(String data) {
        final int lastIndex = rows.size();

        rows.add(data);

        return lastIndex;
    }

    public String update(int index, String newData) {
        final String oldData = rows.get(index);

        rows.set(index, newData);

        return oldData;
    }

    public String delete(int index) {
        final String oldData = rows.get(index);

        rows.remove(index);

        return oldData;
    }
}

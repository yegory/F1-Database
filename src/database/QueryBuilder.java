package database;

import java.util.ArrayList;

public class QueryBuilder {
    public QueryBuilder() {
    }

    public String buildSelect(String tableName, ArrayList<String> attributes, String criteria) {
        String query = "";
        query += "SELECT ";

        if (attributes.isEmpty()) {
            query += "* ";
        } else {
            query += attributes.get(0);
            for (int i = 1; i < attributes.size(); i++) {
                query += ", " + attributes.get(i);
            }

        }
        query += " FROM " + tableName;

        if (!criteria.isEmpty()) {
            query += " WHERE " + criteria;
        }

        return query;
    }

    public String buildJoin(String tableNameA, String tableNameB, String criteria) {
        String query = "";
        query += "SELECT *  ";

        query += "FROM " + tableNameA;
        query += " INNER JOIN " + tableNameB;

        if (!criteria.isEmpty()) {
            query += " ON " + criteria;
        }

        return query;
    }

    public String buildAggByGroup() {
        return "SELECT * FROM director";
    }

    public String buildDivision() {
        return "SELECT * FROM car";
    }

    public String buildAggWithHaving() {
        return "SELECT * FROM track";
    }

    public String buildNestedAgg() {
        return "SELECT * FROM team";
    }
}

package database;

import java.util.ArrayList;

public class QueryBuilder {
    public static final String AGG_BY_GROUP_QUERY = "SELECT * FROM director";
    public static final String AGG_WITH_HAVING_QUERY = "SELECT * FROM track";
    public static final String NESTED_AGG_QUERY = "SELECT * FROM team";
    public static final String DIVISION_QUERY = "SELECT * FROM car";
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
        return AGG_BY_GROUP_QUERY;
    }

    public String buildDivision() {
        return DIVISION_QUERY;
    }

    public String buildAggWithHaving() {
        return AGG_WITH_HAVING_QUERY;
    }

    public String buildNestedAgg() {
        return NESTED_AGG_QUERY;
    }
}

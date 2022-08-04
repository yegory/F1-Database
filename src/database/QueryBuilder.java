package database;

import model.Entity;

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
            for (String attribute : attributes) {
                query += attribute + " ";
            }
        }
        query += "FROM " + tableName;

        if (!criteria.isEmpty()) {
            query += " WHERE " + criteria;
        }

        return query;
    }

    public String buildProject(String tableName, ArrayList<String> attributes) {
        return buildSelect(tableName, attributes, "");
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
}

package database;

import model.Entity.Entity;

import java.util.ArrayList;

public class QueryBuilder {
    public QueryBuilder() {
    }

    public String buildSelect(String tableName, ArrayList<String> attributes, String criteria) {
        String query = "";
        return query;
    }

    public String buildProject(String tableName, ArrayList<String> attributes) {
        String query = "";
        return query;
    }

    public String buildInsert(Entity entity) {
        String query = "INSERT INTO " + entity.getTableName() +
                entity.getTableName() +


        return query;
    }

    public String buildDelete(Entity entity) {
        String query = "";
        return query;
    }

    public String buildUpdate(Entity entity) {
        String query = "";
        return query;
    }
}

package model.Entity;

import java.util.ArrayList;

public abstract class Entity {
    protected String primaryKey;
    protected String tableName;

    protected ArrayList<String> attributes;

    public String getPrimaryKey() {
        return primaryKey;
    }

    public String getTableName() {
        return tableName;
    }
}

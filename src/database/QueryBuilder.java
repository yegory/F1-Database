package database;

import java.util.ArrayList;

public class QueryBuilder {
    public static final String AGG_BY_GROUP_QUERY = "SELECT athleteID, MIN(bestLap) FROM results GROUP BY athleteID";
    public static final String AGG_WITH_HAVING_QUERY = "SELECT sponsorID, max(dealvalue) FROM sponsorsteam GROUP BY sponsorID HAVING sponsorID >= 3";
    public static final String NESTED_AGG_QUERY = "SELECT r1.athleteID, r1.bestLap FROM results r1 WHERE r1.bestLap < (SELECT AVG(r2.bestLap) FROM results r2)";
    public static final String DIVISION_QUERY = "SELECT * FROM sponsor s WHERE NOT EXISTS ((SELECT t.teamID FROM team t) MINUS (SELECT st.teamID FROM sponsorsteam st WHERE st.sponsorID = s.sponsorID))";
    // public static final String DIVISION_QUERY = "SELECT * FROM sponsors s WHERE NOT EXISTS (SELECT s3.teamID FROM sponsorsTeam s3 WHERE s3.sponsorID=s.sponsorID)";
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
        query += " NATURAL JOIN " + tableNameB;

        if (!(criteria.isEmpty())) {
            query += " WHERE " + criteria;
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

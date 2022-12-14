package database;

import util.PrintablePreparedStatement;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import javax.xml.transform.Result;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;

/**
 * !!! Got this class from example project, just playing around with the code !!!
 *
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
    // private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    public static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    public static final String EXCEPTION_TAG = "[EXCEPTION]";
    public static final String WARNING_TAG = "[WARNING]";

    private DirectorHandler directorHandler;
    private ResultsHandler resultsHandler;
    private SponsorsTeamHandler sponsorsTeamHandler;
    private QueryBuilder qb;

    private Connection connection = null;


    private static DatabaseConnectionHandler dbHandler;

    private DatabaseConnectionHandler() {
        // Load the Oracle JDBC driver
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        initiateTableHandlers();
        qb = new QueryBuilder();
    }

    private void initiateTableHandlers() {
        directorHandler = new DirectorHandler();
        resultsHandler = new ResultsHandler();
        sponsorsTeamHandler = new SponsorsTeamHandler();
        // TODO: make the rest of the table handler classes
    }

    public static DatabaseConnectionHandler getHandler() {
        if (dbHandler == null) {
            dbHandler = new DatabaseConnectionHandler();
        }
        return dbHandler;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }


    protected void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public Object[][] select(String tableName, ArrayList<String> attributes, String criteria) {
        Object[][] results = null;
        try {
            String query = qb.buildSelect(tableName, attributes, criteria);
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            results = generateArrayFromSet(rs);

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        print2DArray(results);
        return results;
    }

    public Object[][] project(String tableName, ArrayList<String> attributes) {
        return select(tableName, attributes, "");
    }

    public Object[][] join(String tableNameA, String tableNameB, String criteria) {
        Object[][] results = null;

        try {
            String query = qb.buildJoin(tableNameA, tableNameB, criteria);
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            results = generateArrayFromSet(rs);

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        print2DArray(results);
        return results;
    }

    private Object[][] generateArrayFromSet(ResultSet rs) {
        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        try {
            ResultSetMetaData md = rs.getMetaData();

            ArrayList<Object> labels = new ArrayList<>();
            for (int i = 1; i <= md.getColumnCount(); i++) {
                labels.add(md.getColumnName(i));
            }
            data.add(labels);

            while (rs.next()) {
                ArrayList<Object> dataRow = new ArrayList<>();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    dataRow.add(rs.getObject(i));
                }
                data.add(dataRow);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return convertToArray(data);
    }

    private Object[][] convertToArray(ArrayList<ArrayList<Object>> data) {
        int outputWidth = data.get(0).size();
        int outputHeight = data.size();

        Object[][] outputData = new Object[outputHeight][outputWidth];

        for (int i = 0; i < outputHeight; i++) {
            ArrayList<Object> tempRow = data.get(i);
            for (int j = 0; j < outputWidth; j++) {
                outputData[i][j] = tempRow.get(j);
            }
        }

        return outputData;
    }

    public Object[][] aggByGroup() {
        Object[][] results = null;
        try {
            String query = qb.buildAggByGroup();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            results = generateArrayFromSet(rs);

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        print2DArray(results);
        return results;
    }

    public Object[][] aggWithHaving() {
        Object[][] results = null;
        try {
            String query = qb.buildAggWithHaving();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            results = generateArrayFromSet(rs);

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        print2DArray(results);
        return results;
    }

    public Object[][] nestedAgg() {
        Object[][] results = null;
        try {
            String query = qb.buildNestedAgg();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            results = generateArrayFromSet(rs);

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        print2DArray(results);
        return results;
    }

    public Object[][] division() {
        Object[][] results = null;
        try {
            String query = qb.buildDivision();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            results = generateArrayFromSet(rs);

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        print2DArray(results);
        return results;
    }

    public void print2DArray(Object[][] outputData) {
        for (int i = 0; i < outputData.length; i++) {
            for (int j = 0; j < outputData[0].length; j++) {
                System.out.print(outputData[i][j].toString() + " ");
            }
            System.out.println("");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public DirectorHandler getDirectorHandler() {
        return directorHandler;
    }

    public ResultsHandler getResultsHandler() {
        return resultsHandler;
    }
    public SponsorsTeamHandler getSponsorsTeamHandler() {
        return sponsorsTeamHandler;
    }
}
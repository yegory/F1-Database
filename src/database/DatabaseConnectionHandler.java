package database;

import util.PrintablePreparedStatement;

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

    protected Connection connection = null;
    private QueryBuilder qb;

    private static DatabaseConnectionHandler dbHandler;

    private DatabaseConnectionHandler() {
        // Load the Oracle JDBC driver
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        qb = new QueryBuilder();
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

    public void select(String tableName, ArrayList<String> attributes, String criteria) {
        try {
            String query = qb.buildSelect(tableName, attributes, criteria);
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            // do something with result set


            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void project(String tableName, ArrayList<String> attributes) {
        try {
            String query = qb.buildProject(tableName, attributes);
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            // do something with result set

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public void join(String tableNameA, String tableNameB, String criteria) {
        try {
            String query = qb.buildJoin(tableNameA, tableNameB, criteria);
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            // do something with result set
            // call to Matts function to parse resultset

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /*
        private void dropSponsorTableIfExists() {
        try {
            String query = "select table_name from user_tables";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("sponsor")) {
                    ps.execute("DROP TABLE sponsor");
                    break;
                }
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public SponsorModel[] getBranchInfo() {
        ArrayList<SponsorModel> result = new ArrayList<SponsorModel>();

        try {
            String query = "SELECT * FROM sponsor";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                SponsorModel model = new SponsorModel(
                        rs.getInt("sponsorID"),
                        rs.getString("name"));
                result.add(model);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new SponsorModel[result.size()]);
    }

    private void dropTrackTableIfExists() {
        try {
            String query = "select table_name from user_tables";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("track")) {
                    ps.execute("DROP TABLE track");
                    break;
                }
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertTrack(TrackModel track) {
        try {
            String query = "INSERT INTO track VALUES (?,?,?,?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, track.getTrackID());
            ps.setString(2, track.getTrackName());
            ps.setFloat(3, track.getLength());
            ps.setInt(4, track.getAddressNumber());
            ps.setString(5, track.getStreet());
            ps.setString(6, track.getZipCode());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

        public void insertSponsor(SponsorModel model) {
        try {
            String query = "INSERT INTO sponsor VALUES (?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, model.getSponsorID());
            ps.setString(2, model.getName());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void databaseSetup() {
        dropSponsorTableIfExists();

        try {
            String query = "CREATE TABLE Sponsor(sponsorID INTEGER,name CHAR(50), PRIMARY KEY (sponsorID))";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        SponsorModel sponsor1 = new SponsorModel(1, "DHL");
        SponsorModel sponsor2 = new SponsorModel(2, "Crypto.com");
        SponsorModel sponsor3 = new SponsorModel(3, "Heineken N.V.");
        SponsorModel sponsor4 = new SponsorModel(4, "Rolex Watch Co. Ltd");
        SponsorModel sponsor5 = new SponsorModel(5, "Amazon Web Services, Inc.");

        insertSponsor(sponsor1);
        insertSponsor(sponsor2);
        insertSponsor(sponsor3);
        insertSponsor(sponsor4);
        insertSponsor(sponsor5);

        dropTrackTableIfExists();

        try {
            String query = "CREATE TABLE track (trackID INTEGER PRIMARY KEY, " +
                    "trackName CHAR(80), " +
                    "length FLOAT, " +
                    "addressNumber INTEGER, " +
                    "street CHAR(80), " +
                    "zipCode CHAR(50) NOT NULL)";
            // no foreign key reference to a table that doesn't exist
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        TrackModel track1 = new TrackModel(1, "test track", 100, 1234, "test street", "test zip");
        insertTrack(track1);
    }
     */

}
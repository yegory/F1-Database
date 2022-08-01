package database;

import model.Entity.TrackModel;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * !!! Got this class from example project, just playing around with the code !!!
 *
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
    // private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        // Load the Oracle JDBC driver
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
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

    public void databaseSetup() {
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

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}

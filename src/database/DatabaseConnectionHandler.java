package database;

import model.Entity.TrackModel;
import util.PrintablePreparedStatement;

import model.Entity.SponsorModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        // Load the Oracle JDBC driver
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
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

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
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

}
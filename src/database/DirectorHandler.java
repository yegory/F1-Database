package database;

import model.Director;
import model.SponsorsTeam;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

import static database.DatabaseConnectionHandler.EXCEPTION_TAG;
import static database.DatabaseConnectionHandler.WARNING_TAG;

public class DirectorHandler {

    private Connection connection;

    public DirectorHandler() {
    }

    private void updateConnection() {
        DatabaseConnectionHandler dbHandler = DatabaseConnectionHandler.getHandler();
        this.connection = dbHandler.getConnection();
    }

    public void insertDirector(Director dir) {
        updateConnection();
        try {
            String query = "INSERT INTO director VALUES (?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.directorID());
            ps.setString(2, dir.firstName());
            ps.setString(3, dir.lastName());
            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }

    public void deleteDirector(Director dir) {
        updateConnection();
        try {
            String query = "DELETE FROM director WHERE directorID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.directorID());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Branch " + dir.directorID() + " does not exist!");
            }

            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }

    public void updateDirector(Director dir) {
        updateConnection();
        try {
            String query = "UPDATE director SET directorID = ?, firstName = ?, lastName = ? WHERE directorID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.directorID());
            ps.setString(2, dir.firstName());
            ps.setString(3, dir.lastName());
            ps.setInt(4, dir.directorID());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Director " + dir.directorID() + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }

    public void insertSponsorsTeam(SponsorsTeam st) {
        updateConnection();
        try {
            String query = "INSERT INTO SPONSORSTEAM VALUES (?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, st.sponsorID());
            ps.setInt(2, st.teamID());
            ps.setInt(3, st.dealValue());
            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }
}

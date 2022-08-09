package database;

import model.Director;
import model.Results;
import model.SponsorsTeam;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

import static database.DatabaseConnectionHandler.EXCEPTION_TAG;
import static database.DatabaseConnectionHandler.WARNING_TAG;

public class ResultsHandler {

    private Connection connection;

    public ResultsHandler() {
    }

    private void updateConnection() {
        DatabaseConnectionHandler dbHandler = DatabaseConnectionHandler.getHandler();
        this.connection = dbHandler.getConnection();
    }

    public void deleteResults(Results results) {
        updateConnection();
        try {
            String query = "DELETE FROM results WHERE resultID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, results.resultsID());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Result " + results.resultsID() + " does not exist!");
            }

            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }

    public void deleteSponsorsTeam(SponsorsTeam sponsorsTeam) {
        updateConnection();
        try {
            String query = "DELETE FROM SPONSORSTEAM WHERE sponsorID = ? AND teamID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, sponsorsTeam.sponsorID());
            ps.setInt(2, sponsorsTeam.teamID());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " SponsorsTeam " + sponsorsTeam.sponsorID() + ", " + sponsorsTeam.teamID() + " does not exist!");
            }

            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }
}

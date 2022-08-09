package database;

import model.SponsorsTeam;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

import static database.DatabaseConnectionHandler.EXCEPTION_TAG;

public class SponsorsTeamHandler {

    private Connection connection;

    public SponsorsTeamHandler() {
    }

    private void updateConnection() {
        DatabaseConnectionHandler dbHandler = DatabaseConnectionHandler.getHandler();
        this.connection = dbHandler.getConnection();
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

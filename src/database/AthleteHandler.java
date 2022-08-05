package database;

import model.Director;
import model.Athlete;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

import static database.DatabaseConnectionHandler.EXCEPTION_TAG;
import static database.DatabaseConnectionHandler.WARNING_TAG;

public class AthleteHandler {

    private Connection connection;

    public AthleteHandler() {
    }

    private void updateConnection() {
        DatabaseConnectionHandler dbHandler = DatabaseConnectionHandler.getHandler();
        this.connection = dbHandler.getConnection();
    }

    public void insertAthlete(Athlete dir) {
        updateConnection();
        try {
            String query = "INSERT INTO athlete VALUES (?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.athleteID());
            ps.setInt(2, dir.teamID());
            ps.setString(3, dir.firstName());
            ps.setString(4, dir.lastName());
            ps.setDate(5, dir.dateOfBirth());
            ps.setInt(6, dir.nRaces());
            ps.setDate(7, dir.startDate());
            ps.setDate(8, dir.endDate());
            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }

    public void deleteAthlete(Athlete dir) {
        updateConnection();
        try {
            String query = "DELETE FROM athlete WHERE athleteID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.athleteID());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Branch " + dir.athleteID() + " does not exist!");
            }

            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }

    public void updateAthlete(Athlete dir) {
        updateConnection();
        try {
            String query = "UPDATE athlete SET athleteID = ?, teamID = ?, firstName = ?, lastName = ?, dob = ?, nRaces = ?, startDate = ?, endDate = ? WHERE athleteID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.athleteID());
            ps.setInt(2, dir.teamID());
            ps.setString(3, dir.firstName());
            ps.setString(4, dir.lastName());
            ps.setDate(5, dir.dateOfBirth());
            ps.setInt(6, dir.nRaces());
            ps.setDate(7, dir.startDate());
            ps.setDate(8, dir.endDate());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Athlete " + dir.athleteID() + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }
}

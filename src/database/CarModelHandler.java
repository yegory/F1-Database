package database;

import model.CarModel;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

import static database.DatabaseConnectionHandler.EXCEPTION_TAG;
import static database.DatabaseConnectionHandler.WARNING_TAG;

public class CarModelHandler {

    private Connection connection;

    public CarModelHandler() {
    }

    private void updateConnection() {
        DatabaseConnectionHandler dbHandler = DatabaseConnectionHandler.getHandler();
        this.connection = dbHandler.getConnection();
    }

    public void insertCarModel(CarModel dir) {
        updateConnection();
        try {
            String query = "INSERT INTO carModel VALUES (?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.carModel());
            ps.setFloat(2, dir.weight());
            ps.setFloat(3, dir.topSpeed());
            ps.setInt(3, dir.horsepower());
            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }

    public void deleteCarModel(CarModel dir) {
        updateConnection();
        try {
            String query = "DELETE FROM carModel WHERE carModel = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.carModel());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Branch " + dir.carModel() + " does not exist!");
            }

            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }

    public void updateCarModel(CarModel dir) {
        updateConnection();
        try {
            String query = "UPDATE carModel SET carModel = ?, weight = ?, topSpeed = ?, horsepower = ? WHERE carModel = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.carModel());
            ps.setFloat(2, dir.weight());
            ps.setFloat(3, dir.topSpeed());
            ps.setInt(3, dir.horsepower());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " CarModel " + dir.carModel() + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.getHandler().rollbackConnection();
        }
    }
}

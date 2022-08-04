package database;

import model.Director;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

import static database.DatabaseConnectionHandler.EXCEPTION_TAG;
import static database.DatabaseConnectionHandler.WARNING_TAG;

public class DirectorHandler {

    private Connection connection;

    public DirectorHandler(Connection connection) {
        this.connection = connection;
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

    private void updateConnection() {
        DatabaseConnectionHandler dbHandler = DatabaseConnectionHandler.getHandler();
        this.connection = dbHandler.getConnection();
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

    /*
    public ResultSet selectDirector(ArrayList<String> attributes, String criteria) {
        ResultSet rs = null;
        try {
            String query = selectQueryBuilder("Director", attributes, criteria);
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return rs;
    }


    public ResultSet projectDirector(ArrayList<String> attributes) {
        return selectDirector(attributes, "");
    }


    private String selectQueryBuilder(String tableName, ArrayList<String> attributes, String criteria) {
        String query = "";
        query += "SELECT ";

        if (attributes.isEmpty()) {
            query += "* ";
        } else {
            for (String attribute : attributes) {
                query += attribute + " ";
            }
        }
        query += "FROM " + tableName;

        if (!criteria.isEmpty()) {
            query += " WHERE " + criteria;
        }

        return query;
    }

     */
}

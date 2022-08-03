package database;

import model.EntityInterfaces.Director;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static database.DatabaseConnectionHandler.EXCEPTION_TAG;
import static database.DatabaseConnectionHandler.WARNING_TAG;

public class DirectorHandler {
    private Connection connection;

    public DirectorHandler(Connection connection) {
        this.connection = connection;
    }

    public void insertDirector(Director dir) {
        try {
            String query = "INSERT INTO director VALUES (?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.getDirectorID());
            ps.setString(2, dir.getFirstName());
            ps.setString(3, dir.getLastName());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteDirector(Director dir) {
        try {
            String query = "DELETE FROM director WHERE directorID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.getDirectorID());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Branch " + dir.getDirectorID() + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateDirector(Director dir) {
        try {
            String query = "UPDATE director SET directorID = ?, firstName = ?, lastName = ? WHERE branch_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, dir.getDirectorID());
            ps.setString(2, dir.getFirstName());
            ps.setString(3, dir.getLastName());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Branch " + dir.getDirectorID() + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

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

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}

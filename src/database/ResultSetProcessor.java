package database;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

public class ResultSetProcessor {
    // Singleton should maintain unique connection for all queries
    private static Connection conn;
    private static ResultSetProcessor instance = new ResultSetProcessor();

    private ResultSetProcessor() {
        //private to prevent instantiating
    }

    public static ResultSetProcessor getInstance() {
        return instance;
    }

    public static void setConnection(Connection connection) {
        conn = connection;
    }

    public void ProcessResultSet(String query) throws SQLException {
        String tempQuery = "SELECT * FROM employees WHERE id=?"; // temp query
        QueryRunner queryRunner = new QueryRunner();

        ResultSetHandler<Object[]> handler = new ResultSetHandler<Object[]>() {
            public Object[] handle(ResultSet rs) throws SQLException {
                if (!rs.next()) {
                    return null;
                }
                ResultSetMetaData meta = rs.getMetaData();
                int cols = meta.getColumnCount();
                Object[] result = new Object[cols];

                for (int i = 0; i < cols; i++) {
                    result[i] = rs.getObject(i + 1);
                }
                return result;
            }
        };

        try {
            Object[] result  = queryRunner.query(conn, tempQuery,
                    handler, 103);
            // Display values
            System.out.print("Result: " + Arrays.toString(result));
            // Plug in UI formatting or data usage
        } finally {
//            DbUtils.close(conn); // might not want to close the connection on each query parse
        }
    }
}

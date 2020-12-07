package dbUtils;

import java.sql.*;

public class SqliteUtils {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:yahtzee.db");
    }

    public static void closeConnection(ResultSet rs,Statement stmt,Connection conn){
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(conn != null && !conn.isClosed() ) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

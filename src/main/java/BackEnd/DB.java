package BackEnd;

import java.sql.*;

public class DB {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/menu_items_db";
    private static final String user = "root";
    private static final String pass = "Reddam2021";
    private static Connection conn;

    public static void update(String update) throws SQLException {//Method allowing for SQL update,insert and delete statements

        Connection conn = DriverManager.getConnection(url, user, pass);
        PreparedStatement statement = conn.prepareStatement(update);
        statement = conn.prepareStatement(update);
        statement.executeUpdate();
        statement.close();
    }

    public static ResultSet query(String stmt) throws SQLException {//Methods allowing for extracting data from a table
        Connection conn = DriverManager.getConnection(url, user, pass);
        PreparedStatement statement = conn.prepareStatement(stmt);

        ResultSet resultSet = statement.executeQuery(stmt);
        return resultSet;
    }
    
    public static void connect() throws ClassNotFoundException, SQLException{
        
        if (conn == null) {
            Class.forName(driver);
            System.out.println("Driver found");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connected");
        }
    }

}

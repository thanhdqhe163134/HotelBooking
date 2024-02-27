package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String url  = "jdbc:sqlserver://DESKTOP-1ULH9CF\\SQLEXPRESS:1433;databaseName=HotelBooking";
    private static final String user  = "thanhdq";
    private static final String password  = "123456";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

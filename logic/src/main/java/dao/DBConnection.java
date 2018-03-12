package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static Connection connection;

    private static String JDBC_DRIVER ;
    private static String DB_URL;
    private static String USER;
    private static String PASSWORD;

    static {
        Properties databaseProperties = new Properties();
        try {
            databaseProperties.load(DBConnection.class.getResourceAsStream("db.properties"));
            JDBC_DRIVER = databaseProperties.getProperty("db.driverClass");
            DB_URL = databaseProperties.getProperty("db.url");
            USER = databaseProperties.getProperty("db.user");
            PASSWORD = databaseProperties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                return connection;
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


}

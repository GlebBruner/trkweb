package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String DBMS_NAME = "h2";

    private static Connection connection;

    private static String JDBC_DRIVER ;
    private static String DB_URL;
    private static String USER;
    private static String PASSWORD;

    static {
        Properties databaseProperties = new Properties();
        try {
            databaseProperties.load(DBConnection.class.getClassLoader().getResourceAsStream("h2.properties"));
            JDBC_DRIVER = databaseProperties.getProperty("database.DriverClassName");
            DB_URL = databaseProperties.getProperty("database.Url");
            USER = databaseProperties.getProperty("database.User");
            PASSWORD = databaseProperties.getProperty("database.Password");
        } catch (Exception e) {
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

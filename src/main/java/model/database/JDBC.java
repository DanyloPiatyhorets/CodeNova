package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
// https://sst-stuproj.city.ac.uk/phpmyadmin/index.php?route=/database/structure&db=in2033t29
    private static final String URL = "jdbc:mysql://sst-stuproj.city.ac.uk:3306/in2033t29";
    private static final String USER = "in2033t29_a";
    private static final String PASSWORD = "q6yzPzkePog";
    //Database name:  in2033t29

    //Admin user:  in2033t29_a
    //Admin password: q6yzPzkePog

    //Data user:  in2033t29_d
    //Data password: 8VHRrnzsM60



    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
        return connection;
    }
}

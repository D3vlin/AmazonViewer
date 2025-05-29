package com.d3vlin13.amazonviewer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.d3vlin13.amazonviewer.db.DataBase.*;

public class DBConnectionManager {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. " + e);
        }
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL+DB, USER, PASSWORD);

            if (connection != null) {
                System.out.println("DB Connected!");
            }
            return connection;

        } catch (SQLException e) {
            System.out.println("Database connection failed. " + e);
            return null;
        }
    }
}

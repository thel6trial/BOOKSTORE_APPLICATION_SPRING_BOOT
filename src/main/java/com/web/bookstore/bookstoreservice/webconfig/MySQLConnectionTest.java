package com.web.bookstore.bookstoreservice.webconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bookstore";
        String username = "root";
        String password = "glassonion123";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to MySQL database established successfully.");
            // Perform additional tests or database operations here
            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to connect to MySQL database: " + e.getMessage());
        }
    }
}

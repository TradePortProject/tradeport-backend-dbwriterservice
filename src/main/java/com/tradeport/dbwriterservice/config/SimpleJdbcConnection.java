package com.tradeport.dbwriterservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleJdbcConnection {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public SimpleJdbcConnection() {
        System.out.println("SimpleJdbcConnection bean initialized!");
    }
    public Connection getConnection() throws SQLException {
        try {
            dbUrl="jdbc:sqlserver://localhost:1433;databaseName=tradeportdb;encrypt=true;trustServerCertificate=true";
            dbUser="sa";
            dbPassword="Your_password123";
            System.err.println("dbUrl: " + dbUrl);
            System.err.println("dbUser: " + dbUser);
            System.err.println("dbPassword: " + dbPassword);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Load the SQL Server JDBC driver
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC driver not found", e);
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }
}
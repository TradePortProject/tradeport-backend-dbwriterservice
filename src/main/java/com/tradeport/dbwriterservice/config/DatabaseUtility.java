package com.tradeport.dbwriterservice.config;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUtility {

    private final DataSource dataSource;

    @Autowired
    public DatabaseUtility(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void testConnection() {
        try (Connection connection = getConnection()) {
            System.out.println("Database connection successful: " + connection.getMetaData().getURL());
        } catch (SQLException e) {
            System.err.println("Failed to establish database connection: " + e.getMessage());
        }
    }
}

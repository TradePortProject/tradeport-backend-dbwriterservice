package com.tradeport.dbwriterservice.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=tradeportdb;encrypt=true;trustServerCertificate=true",
        "spring.datasource.username=testUser",
        "spring.datasource.password=testPass"
})
class DatabaseConnectionTest {

    @InjectMocks
    private DatabaseConnection databaseConnection;

    @Test
    void testDataSourceInitialization() {
        DataSource dataSource = databaseConnection.dataSource();
        assertNotNull(dataSource);
        assertTrue(dataSource instanceof HikariDataSource);

        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        assertEquals("jdbc:sqlserver://localhost:1433;databaseName=tradeportdb;encrypt=true;trustServerCertificate=true", hikariDataSource.getJdbcUrl());
        assertEquals("testUser", hikariDataSource.getUsername());
        assertEquals("testPass", hikariDataSource.getPassword());
        assertEquals(10, hikariDataSource.getMaximumPoolSize());
        assertEquals(2, hikariDataSource.getMinimumIdle());
    }
}

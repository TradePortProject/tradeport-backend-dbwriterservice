package com.tradeport.dbwriterservice.repository;

import com.tradeport.dbwriterservice.model.NotificationTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NotificationDAOImplTest {

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @InjectMocks
    private NotificationDAOImpl notificationDAO;

    private NotificationTO notificationTO;

    @BeforeEach
    void setUp() throws SQLException {
        // Mock the database connection and prepared statement
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        // Initialize a test NotificationTO object
        notificationTO = new NotificationTO();
        notificationTO.setNotificationID(UUID.randomUUID());
        notificationTO.setUserID(UUID.randomUUID());
        notificationTO.setSubject("Test Subject");
        notificationTO.setMessage("Test Message");
        notificationTO.setCreatedOn(new Date());
        notificationTO.setCreatedBy(UUID.randomUUID());
        notificationTO.setEmailSend(true);
        notificationTO.setFromEmail("test@tradeport.com");
        notificationTO.setRecipientEmail("recipient@tradeport.com");
        notificationTO.setFailureReason(null);
        notificationTO.setSentTime(new Date());
    }

    @Test
    void testInsert_Success() throws SQLException {
        notificationDAO.insert(notificationTO);

        verify(preparedStatement, times(1)).setObject(1, notificationTO.getNotificationID());
        verify(preparedStatement, times(1)).setObject(2, notificationTO.getUserID());
        verify(preparedStatement, times(1)).setString(3, notificationTO.getSubject());
        verify(preparedStatement, times(1)).setString(4, notificationTO.getMessage());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testInsert_SQLExceptionHandling() throws SQLException {
        doThrow(new SQLException("Database error")).when(preparedStatement).executeUpdate();

        SQLException exception = assertThrows(SQLException.class, () -> notificationDAO.insert(notificationTO));
        assertTrue(exception.getMessage().contains("Error inserting notification data"));
    }
}

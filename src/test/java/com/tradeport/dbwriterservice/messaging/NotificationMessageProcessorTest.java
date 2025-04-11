package com.tradeport.dbwriterservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeport.dbwriterservice.model.NotificationTO;
import com.tradeport.dbwriterservice.repository.DatabaseDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationMessageProcessorTest {

    @Mock
    private DatabaseDAO<NotificationTO> databaseDAO;

    @InjectMocks
    private NotificationMessageProcessor notificationMessageProcessor;

    private final String validJsonMessage = "{\"subject\":\"Test Subject\",\"message\":\"Test Message\",\"recipientEmail\":\"test@example.com\"}";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Correctly initialize mocks
    }

    @Test
    void testTransformData_ValidMessage() throws IOException {
        String transformedData = notificationMessageProcessor.transformData(validJsonMessage);
        assertNotNull(transformedData);

        ObjectMapper objectMapper = new ObjectMapper();
        NotificationTO notificationTO = objectMapper.readValue(transformedData, NotificationTO.class);
        assertNotNull(notificationTO.getNotificationID()); // UUID should be generated
        assertEquals("Test Subject", notificationTO.getSubject());
        assertEquals("Test Message", notificationTO.getMessage());
    }

    @Test
    void testTransformData_InvalidMessage() {
        String invalidMessage = "invalid-message";
        Exception exception = assertThrows(RuntimeException.class, () -> notificationMessageProcessor.transformData(invalidMessage));
        assertTrue(exception.getMessage().contains("Message transformation failed"));
    }

    @Test
    void testSaveToDatabase_Success() throws Exception {
        notificationMessageProcessor.saveToDatabase(validJsonMessage);
        verify(databaseDAO, times(1)).insert(any(NotificationTO.class));
    }

    @Test
    void testSaveToDatabase_InvalidData() {
        String invalidData = "invalid-data";
        Exception exception = assertThrows(RuntimeException.class, () -> notificationMessageProcessor.saveToDatabase(invalidData));
        assertTrue(exception.getMessage().contains("Database save operation failed"));
    }

    @Test
    void testSaveToDatabase_SQLExceptionHandling() throws Exception {
        doThrow(new SQLException("DB error")).when(databaseDAO).insert(any(NotificationTO.class));

        String transformedData = notificationMessageProcessor.transformData(validJsonMessage);
        Exception exception = assertThrows(RuntimeException.class, () -> notificationMessageProcessor.saveToDatabase(transformedData));
        assertTrue(exception.getMessage().contains("Database operation failed"));
    }
}

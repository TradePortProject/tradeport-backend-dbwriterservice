package com.tradeport.dbwriterservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeport.dbwriterservice.model.NotificationTO;
import com.tradeport.dbwriterservice.repository.DAOFactory;
import com.tradeport.dbwriterservice.repository.DatabaseDAO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class NotificationMessageProcessor extends KafkaToDatabaseTemplate {
    private static final Logger LOGGER = Logger.getLogger(NotificationMessageProcessor.class.getName());
    private final DatabaseDAO<NotificationTO> databaseDAO;

    public NotificationMessageProcessor() {
        // Using DAOFactory to create the DAO instance
        this.databaseDAO = DAOFactory.getDatabaseDAO("NOTIF");
    }

    @Override
    protected String transformData(String message) {
        LOGGER.info("Transforming message: " + message);
        try {
            // Parse message into NotificationTO object
            ObjectMapper objectMapper = new ObjectMapper();
            NotificationTO notificationTO = objectMapper.readValue(message, NotificationTO.class);
            notificationTO.setNotificationID(UUID.randomUUID()); // Generate random Notification ID

            LOGGER.info("Transformed NotificationTO: " + notificationTO);
            return objectMapper.writeValueAsString(notificationTO); // Return serialized JSON as transformed data
        } catch (IOException e) {
            LOGGER.severe("Failed to transform message: " + e.getMessage());
            throw new RuntimeException("Message transformation failed", e);
        }
    }

    @Override
    protected void saveToDatabase(String data) {
        LOGGER.info("Saving transformed data to database.");
        try {
            // Deserialize transformed data back into NotificationTO
            ObjectMapper objectMapper = new ObjectMapper();
            NotificationTO notificationTO = objectMapper.readValue(data, NotificationTO.class);

            // Insert NotificationTO into the database
            databaseDAO.insert(notificationTO);
            LOGGER.info("Data successfully saved to database.");
        } catch (IOException e) {
            LOGGER.severe("Failed to parse transformed data: " + e.getMessage());
            throw new RuntimeException("Database save operation failed", e);
        } catch (SQLException e) {
            LOGGER.severe("Database operation failed: " + e.getMessage());
            throw new RuntimeException("Database operation failed", e);
        }
    }
}

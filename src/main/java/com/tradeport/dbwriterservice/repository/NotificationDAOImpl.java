package com.tradeport.dbwriterservice.repository;

import com.tradeport.dbwriterservice.config.DatabaseConnection;
import com.tradeport.dbwriterservice.config.DatabaseUtility;
import com.tradeport.dbwriterservice.config.SimpleJdbcConnection;
import com.tradeport.dbwriterservice.model.NotificationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class NotificationDAOImpl implements DatabaseDAO<NotificationTO> {




    @Override
    public void insert(NotificationTO notificationTO) throws SQLException {
        String query = "INSERT INTO Notification (NotificationID, UserID, Subject, Message, CreatedOn, CreatedBy, EmailSend, FromEmail, RecipientEmail, FailureReason, SentTime) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new SimpleJdbcConnection().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setObject(1, notificationTO.getNotificationID());
            pstmt.setObject(2, notificationTO.getUserID());
            pstmt.setString(3, notificationTO.getSubject());
            pstmt.setString(4, notificationTO.getMessage());
            pstmt.setTimestamp(5, new java.sql.Timestamp(notificationTO.getCreatedOn().getTime()));
            pstmt.setObject(6, notificationTO.getCreatedBy());
            pstmt.setBoolean(7, notificationTO.isEmailSend());
            pstmt.setString(8, notificationTO.getFromEmail());
            pstmt.setString(9, notificationTO.getRecipientEmail());
            pstmt.setString(10, notificationTO.getFailureReason());
            pstmt.setTimestamp(11, new java.sql.Timestamp(notificationTO.getSentTime().getTime()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error inserting notification data", e);
        }
    }
}

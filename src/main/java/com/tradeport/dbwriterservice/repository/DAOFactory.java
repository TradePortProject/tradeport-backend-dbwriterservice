package com.tradeport.dbwriterservice.repository;

import com.tradeport.dbwriterservice.model.NotificationTO;

public class DAOFactory {
    public static DatabaseDAO<NotificationTO> getDatabaseDAO(String dbType) {
        if ("NOTIF".equalsIgnoreCase(dbType)) {
            return new NotificationDAOImpl();
        }
        throw new IllegalArgumentException("Unsupported database type: " + dbType);
    }
}

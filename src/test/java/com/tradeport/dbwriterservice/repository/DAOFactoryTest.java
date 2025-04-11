package com.tradeport.dbwriterservice.repository;

import com.tradeport.dbwriterservice.model.NotificationTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAOFactoryTest {

    @Test
    void testGetDatabaseDAO_ValidType() {
        DatabaseDAO<NotificationTO> dao = DAOFactory.getDatabaseDAO("NOTIF");
        assertNotNull(dao);
        assertTrue(dao instanceof NotificationDAOImpl);
    }

    @Test
    void testGetDatabaseDAO_InvalidType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DAOFactory.getDatabaseDAO("UNKNOWN"));
        assertEquals("Unsupported database type: UNKNOWN", exception.getMessage());
    }
}

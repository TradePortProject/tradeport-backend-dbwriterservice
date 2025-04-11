package com.tradeport.dbwriterservice.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private final KafkaToDatabaseTemplate kafkaToDatabaseTemplate;

    public KafkaConsumer(KafkaToDatabaseTemplate kafkaToDatabaseTemplate) {
        this.kafkaToDatabaseTemplate = kafkaToDatabaseTemplate;
        logger.info("KafkaConsumer initialized and ready to consume messages...");
    }

    @KafkaListener(topics = "tradeport-notified", groupId = "tradeport-group")
    public void consume(String message) {
        try {
            logger.info("Received Kafka message: {}", message);

            if (message == null || message.isEmpty()) {
                logger.warn("Received empty or null Kafka message. Skipping processing.");
                return;
            }

            kafkaToDatabaseTemplate.processMessage(message);
        //} catch (SQLException e) {
        //    logger.error("Database error while processing Kafka message: {}", e.getMessage(), e);
        } catch (SerializationException e) {
            logger.error("Error deserializing Kafka message: {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
        }
    }
}

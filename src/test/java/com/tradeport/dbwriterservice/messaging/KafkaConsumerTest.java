package com.tradeport.dbwriterservice.messaging;

import com.tradeport.dbwriterservice.messaging.KafkaConsumer;
import com.tradeport.dbwriterservice.messaging.KafkaToDatabaseTemplate;
import org.apache.kafka.common.errors.SerializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KafkaConsumerTest {

    @Mock
    private KafkaToDatabaseTemplate kafkaToDatabaseTemplate;

    @InjectMocks
    private KafkaConsumer kafkaConsumer;

    @BeforeEach
    void setUp() {
        kafkaConsumer = new KafkaConsumer(kafkaToDatabaseTemplate);
    }

    @Test
    void testConsume_ValidMessage() {
        String validMessage = "Test Kafka Message";

        kafkaConsumer.consume(validMessage);

        verify(kafkaToDatabaseTemplate, times(1)).processMessage(validMessage);
    }

    @Test
    void testConsume_NullMessage() {
        kafkaConsumer.consume(null);

        verify(kafkaToDatabaseTemplate, never()).processMessage(anyString());
    }

    @Test
    void testConsume_EmptyMessage() {
        kafkaConsumer.consume("");

        verify(kafkaToDatabaseTemplate, never()).processMessage(anyString());
    }

    @Test
    void testConsume_SerializationExceptionHandling() {
        String faultyMessage = "Faulty Message";

        doThrow(new SerializationException("Serialization error")).when(kafkaToDatabaseTemplate).processMessage(faultyMessage);

        kafkaConsumer.consume(faultyMessage);

        verify(kafkaToDatabaseTemplate, times(1)).processMessage(faultyMessage);
    }

    @Test
    void testConsume_UnexpectedExceptionHandling() {
        String message = "Unexpected error message";

        doThrow(new RuntimeException("Unexpected exception")).when(kafkaToDatabaseTemplate).processMessage(message);

        kafkaConsumer.consume(message);

        verify(kafkaToDatabaseTemplate, times(1)).processMessage(message);
    }
}

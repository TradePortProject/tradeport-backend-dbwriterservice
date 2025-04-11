package com.tradeport.dbwriterservice.messaging;

public abstract class KafkaToDatabaseTemplate {
    public void processMessage(String message) {
        System.out.println("Reading message from Kafka: " + message);
        String processedData = transformData(message);
        saveToDatabase(processedData);
    }

    protected abstract String transformData(String message);

    protected abstract void saveToDatabase(String data);
}
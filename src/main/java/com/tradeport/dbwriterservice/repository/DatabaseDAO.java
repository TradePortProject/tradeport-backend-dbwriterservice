package com.tradeport.dbwriterservice.repository;

import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public interface DatabaseDAO<T> {
    void insert(T transferObject) throws SQLException;
}

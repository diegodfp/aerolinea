package com.aerolinea.common.infrastructure.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://monorail.proxy.rlwy.net:27388/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "tDRzVAIHIHIDGGOZHNSKMojzNlePshrI";
    /* 
    private static final String URL = "jdbc:mysql://localhost:3306/aerolinea";
    private static final String USER = "root";
    private static final String PASSWORD = "1098792956";
    */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

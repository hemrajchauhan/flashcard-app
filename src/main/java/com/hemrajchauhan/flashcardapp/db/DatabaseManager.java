package com.hemrajchauhan.flashcardapp.db;

import java.sql.*;
import java.util.*;

public class DatabaseManager {
    private final String dbUrl;

    public DatabaseManager(String dbFilePath){
        this.dbUrl = "jdbc:sqlite:" + dbFilePath;
    }

    public List<String> getAllTables(){
        List<String> tables = new ArrayList<>();
        String sql = "SELECT name FROM sqlite_master WHERE type='table'";
        try (Connection conn = DriverManager.getConnection(dbUrl);
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
                while (rs.next()) {
                    tables.add(rs.getString("name"));
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    public List<String> getTableColumns(String tableName){
        List<String> columns = new ArrayList<>();
        String sql = "PRAGMA table_info(" + tableName + ")";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
                while (rs.next()) {
                    columns.add(rs.getString("name"));
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }
}

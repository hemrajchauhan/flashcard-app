package com.hemrajchauhan.flashcardapp.db;

import java.sql.*;
import java.util.*;

import com.hemrajchauhan.flashcardapp.model.Flashcard;

public class DatabaseManager {
    private final String dbUrl;

    public DatabaseManager(String dbFilePathOrUrl) {
        // If the input already starts with 'jdbc:sqlite:', use as-is; otherwise, prepend
        if (dbFilePathOrUrl.startsWith("jdbc:sqlite:")) {
            this.dbUrl = dbFilePathOrUrl;
        } else {
            this.dbUrl = "jdbc:sqlite:" + dbFilePathOrUrl;
        }
    }

    public List<String> getAllTables() {
        List<String> tables = new ArrayList<>();
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%'";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tables.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    public List<String> getTableColumns(String tableName) {
        List<String> columns = new ArrayList<>();
        String sql = "PRAGMA table_info(" + tableName + ")";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                columns.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    public List<Flashcard> fetchFlashcards(String table, String frontCol, String backCol) {
        List<Flashcard> flashcards = new ArrayList<>();
        String sql = "SELECT \"" + frontCol + "\", \"" + backCol + "\" FROM \"" + table + "\"";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String front = rs.getString(frontCol);
                String back = rs.getString(backCol);
                flashcards.add(new Flashcard(front, back));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flashcards;
    }
}

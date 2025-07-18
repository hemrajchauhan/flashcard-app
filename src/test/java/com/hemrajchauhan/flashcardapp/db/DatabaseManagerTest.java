package com.hemrajchauhan.flashcardapp.db;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import com.hemrajchauhan.flashcardapp.model.Flashcard;

import static org.junit.Assert.*;

public class DatabaseManagerTest {
    private static Connection sharedConn;
    private static final String DB_URI = "jdbc:sqlite:file:memdb1?mode=memory&cache=shared";
    private DatabaseManager dbManager;

    @BeforeClass
    public static void initConn() throws Exception {
        sharedConn = DriverManager.getConnection(DB_URI);
        try (Statement stmt = sharedConn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS test_table (id INTEGER PRIMARY KEY, front TEXT, back TEXT)");
            stmt.execute("DELETE FROM test_table");
            stmt.execute("INSERT INTO test_table (front, back) VALUES ('Q1', 'A1'), ('Q2', 'A2')");
        }
    }

    @AfterClass
    public static void closeConn() throws Exception {
        if (sharedConn != null) sharedConn.close();
    }

    @Before
    public void setUp() {
        dbManager = new DatabaseManager(DB_URI);
    }

    @Test
    public void testGetAllTables() {
        List<String> tables = dbManager.getAllTables();
        assertTrue("Should contain 'test_table'", tables.contains("test_table"));
        assertFalse("Should not contain sqlite_ tables", tables.stream().anyMatch(t -> t.startsWith("sqlite_")));
    }

    @Test
    public void testGetTableColumns() {
        List<String> columns = dbManager.getTableColumns("test_table");
        assertTrue("Should contain 'front'", columns.contains("front"));
        assertTrue("Should contain 'back'", columns.contains("back"));
        assertTrue("Should contain 'id'", columns.contains("id"));
    }

    @Test
    public void testFetchFlashcards() {
        List<Flashcard> cards = dbManager.fetchFlashcards("test_table", "front", "back");
        assertEquals(2, cards.size());
        assertEquals("Q1", cards.get(0).getFront());
        assertEquals("A1", cards.get(0).getBack());
        assertEquals("Q2", cards.get(1).getFront());
        assertEquals("A2", cards.get(1).getBack());
    }
}

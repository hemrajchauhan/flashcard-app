package com.hemrajchauhan.flashcardapp.ui;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.*;
import java.util.concurrent.FutureTask;

import static org.junit.Assert.*;

public class TableSelectorDialogTestFX extends ApplicationTest {
    @Override
    public void start(Stage stage) {}

    @Test
    public void testSelectTablePicksFirst() throws Exception {
        var tables = Arrays.asList("animals", "words", "math");
        FutureTask<Optional<String>> task = new FutureTask<>(() -> TableSelectorDialog.selectTable(tables));
        Platform.runLater(task);
        Thread.sleep(500);
        clickOn("OK");
        Optional<String> selected = task.get();
        assertTrue(selected.isPresent());
        assertEquals("animals", selected.get());
    }

    @Test
    public void testSelectColumnPicksFirst() throws Exception {
        var columns = Arrays.asList("front", "back");
        FutureTask<Optional<String>> task = new FutureTask<>(() -> TableSelectorDialog.selectColumn(columns, "front"));
        Platform.runLater(task);
        Thread.sleep(500);
        clickOn("OK");
        Optional<String> selected = task.get();
        assertTrue(selected.isPresent());
        assertEquals("front", selected.get());
    }
}

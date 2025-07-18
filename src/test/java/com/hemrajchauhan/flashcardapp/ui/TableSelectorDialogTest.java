package com.hemrajchauhan.flashcardapp.ui;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.FutureTask;

import javafx.application.Platform;
import javafx.stage.Stage;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

public class TableSelectorDialogTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {}

    @Test
    public void testSelectTablePicksFirst() throws Exception {
        var tables = Arrays.asList("animals", "words", "math");
        FutureTask<Optional<String>> task = new FutureTask<>(() -> TableSelectorDialog.selectTable(tables));
        Platform.runLater(task);

        // Wait for dialog to open and FX thread to process events
        WaitForAsyncUtils.waitForFxEvents();

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

        WaitForAsyncUtils.waitForFxEvents();

        clickOn("OK");

        Optional<String> selected = task.get();
        assertTrue(selected.isPresent());
        assertEquals("front", selected.get());
    }
}
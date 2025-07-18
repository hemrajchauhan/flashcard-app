package com.hemrajchauhan.flashcardapp;

import java.util.List;
import java.util.Optional;

import com.hemrajchauhan.flashcardapp.db.DatabaseManager;
import com.hemrajchauhan.flashcardapp.ui.TableSelectorDialog;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage primaryStage) {
         // Choose DB file                  
        DatabaseManager dbManager = new DatabaseManager("data/flashcards.db");

        // Get tables
        List<String> tables = dbManager.getAllTables();
        Optional<String> selectedTable = TableSelectorDialog.selectTable(tables);
        if (selectedTable.isEmpty()) {
            primaryStage.setScene(new Scene(new Label("No table selected. Exiting."), 300, 100));
            primaryStage.show();
            return;
        }

        // Get columns
        List<String> columns = dbManager.getTableColumns(selectedTable.get());
        Optional<String> frontCol = TableSelectorDialog.selectColumn(columns, "front");
        Optional<String> backCol = TableSelectorDialog.selectColumn(columns, "back");
        if (frontCol.isEmpty() || backCol.isEmpty()) {
            primaryStage.setScene(new Scene(new Label("No columns selected. Exiting."), 300, 100));
            primaryStage.show();
            return;
        }

        // For now, just show what was picked
        Label label = new Label("Table: " + selectedTable.get()
                + "\nFront: " + frontCol.get()
                + "\nBack: " + backCol.get());
        primaryStage.setScene(new Scene(label, 400, 200));
        primaryStage.show();
    }

    public static void main( String[] args )
    {
        launch(args);
    }
}

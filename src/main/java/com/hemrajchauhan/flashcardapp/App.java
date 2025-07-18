package com.hemrajchauhan.flashcardapp;

import java.util.List;
import java.util.Optional;

import com.hemrajchauhan.flashcardapp.db.DatabaseManager;
import com.hemrajchauhan.flashcardapp.model.Deck;
import com.hemrajchauhan.flashcardapp.model.Flashcard;
import com.hemrajchauhan.flashcardapp.ui.StudyView;
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

        // Fetch flashcards from database
        List<Flashcard> cards = dbManager.fetchFlashcards(selectedTable.get(), frontCol.get(), backCol.get());

        // Create deck (you can name it the same as the table)
        Deck deck = new Deck(selectedTable.get(), cards);

        // Launch Study Mode
        new StudyView(deck).show(primaryStage);
    }

    public static void main( String[] args )
    {
        launch(args);
    }
}

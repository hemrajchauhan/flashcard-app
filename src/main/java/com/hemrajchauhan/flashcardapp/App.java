package com.hemrajchauhan.flashcardapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage primaryStage) {
        // Left: Decks List
        ListView<String> deckList = new ListView<>();
        deckList.getItems().addAll("Default Deck"); // placeholder

        // Right: Flashcards List
        ListView<String> flashcardList = new ListView<>();
        flashcardList.getItems().addAll("Q: What is Java?\nA: A programming language."); // placeholder

        // Top: Buttons
        Button addDeckBtn = new Button("Add Deck");
        Button addCardBtn = new Button("Add Flashcard");

        HBox topBar = new HBox(10, addDeckBtn, addCardBtn);

        // Layout
        BorderPane root = new BorderPane();
        root.setLeft(deckList);
        root.setCenter(flashcardList);
        root.setTop(topBar);

        // Scene
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Flashcard App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main( String[] args )
    {
        launch(args);
    }
}

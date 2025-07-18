package com.hemrajchauhan.flashcardapp.ui;

import com.hemrajchauhan.flashcardapp.model.Deck;
import com.hemrajchauhan.flashcardapp.model.Flashcard;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class StudyView {
    private int currentIndex = 0;
    private boolean showingFront = true;
    private final Deck deck;
    private final Label cardLabel = new Label();
    private final Button nextButton = new Button("Next");
    private final ProgressBar progressBar = new ProgressBar();

    public StudyView(Deck deck) {
        this.deck = deck;
    }

    public void show(Stage stage) {
        // --- Set IDs for TestFX lookup ---
        cardLabel.setId("flashcard-label");
        nextButton.setId("next-card-btn");
        progressBar.setId("progress-bar");

        cardLabel.setWrapText(true);
        cardLabel.setAlignment(Pos.CENTER);
        cardLabel.setTextFill(Color.web("#333"));
        cardLabel.setMaxWidth(Double.MAX_VALUE);
        cardLabel.setMaxHeight(Double.MAX_VALUE);
        cardLabel.setPadding(new Insets(30));
        cardLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(24), Insets.EMPTY)));
        cardLabel.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(24), new BorderWidths(2))));
        cardLabel.setEffect(new DropShadow(18, Color.rgb(55, 124, 246, 0.3)));

        cardLabel.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", cardLabel.heightProperty().divide(7).asString(), "px; -fx-font-weight: bold;")
        );

        StackPane cardPane = new StackPane(cardLabel);
        cardPane.setMinHeight(200);
        cardPane.setMaxWidth(Double.MAX_VALUE);

        Label overlay = new Label("Click card to flip");
        overlay.setTextFill(Color.web("#377cf6"));
        overlay.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        overlay.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,255,0.7), new CornerRadii(18), Insets.EMPTY)));
        overlay.setPadding(new Insets(5, 12, 5, 12));
        StackPane.setAlignment(overlay, Pos.BOTTOM_CENTER);
        StackPane cardWithOverlay = new StackPane(cardPane, overlay);

        HBox buttonBox = new HBox(nextButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);

        progressBar.setPrefHeight(12);
        progressBar.setPrefWidth(300);
        progressBar.setStyle("-fx-accent: #77e27c;");

        VBox root = new VBox(18, progressBar, cardWithOverlay, buttonBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(32));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right,#E7F1FE 50%,#d7fde7 100%);");
        VBox.setVgrow(cardWithOverlay, Priority.ALWAYS);

        if (deck.getCards().isEmpty()) {
            cardLabel.setText("No flashcards in this deck.");
            nextButton.setDisable(true);
            overlay.setVisible(false);
            progressBar.setProgress(0);
        } else {
            showCard();
            progressBar.setProgress((currentIndex + 1.0) / deck.getCards().size());
        }

        cardLabel.setOnMouseClicked(_ -> flipCard(overlay));
        nextButton.setOnAction(_ -> {
            currentIndex++;
            if (currentIndex >= deck.getCards().size()) {
                currentIndex = 0;
            }
            showingFront = true;
            showCard();
            progressBar.setProgress((currentIndex + 1.0) / deck.getCards().size());
            overlay.setVisible(true);
        });

        // Make window responsive
        Scene scene = new Scene(root, 520, 400);
        stage.setMinWidth(400);
        stage.setMinHeight(320);
        stage.setScene(scene);
        stage.setTitle("Study Mode");
        stage.show();
    }

    private void showCard() {
        Flashcard card = deck.getCards().get(currentIndex);
        cardLabel.setText(card.getFront());
        showingFront = true;
    }

    private void flipCard(Label overlay) {
        Flashcard card = deck.getCards().get(currentIndex);
        if (showingFront) {
            cardLabel.setText(card.getBack());
            overlay.setVisible(false);
        } else {
            cardLabel.setText(card.getFront());
            overlay.setVisible(true);
        }
        showingFront = !showingFront;
    }
}

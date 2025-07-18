package com.hemrajchauhan.flashcardapp.ui;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.hemrajchauhan.flashcardapp.model.Deck;
import com.hemrajchauhan.flashcardapp.model.Flashcard;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StudyViewTest extends ApplicationTest {

    private Deck deck;
    private StudyView studyView;

    @Override
    public void start(Stage stage) {
        deck = new Deck("UI Test", Arrays.asList(
                new Flashcard("What is 2+2?", "4"),
                new Flashcard("Capital of France?", "Paris")
        ));
        studyView = new StudyView(deck);
        studyView.show(stage);
    }

    @Test
    public void canSeeFirstCardFront() {
        Label cardLabel = lookup("#flashcard-label").query();
        String text = cardLabel.getText();
        assertTrue("Should see first card's front text", text.contains("2+2"));
    }

    @Test
    public void canFlipCard() {
        Label cardLabel = lookup("#flashcard-label").query();
        assertTrue(cardLabel.getText().contains("2+2"));
        clickOn(cardLabel);
        sleep(200); // Wait for UI thread
        String text = cardLabel.getText();
        assertEquals("4", text.trim());
    }

    @Test
    public void canGoToNextCard() {
        Button nextButton = lookup("#next-card-btn").query();
        clickOn(nextButton);
        sleep(200); // Wait for UI update
        Label cardLabel = lookup("#flashcard-label").query();
        assertTrue("Should see next card's front text", cardLabel.getText().toLowerCase().contains("capital"));
    }
}

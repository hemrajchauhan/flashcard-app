package com.hemrajchauhan.flashcardapp.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlashcardTest {
    @Test
    public void testConstructorAndGetters() {
        Flashcard card = new Flashcard("Capital of France?", "Paris");
        assertEquals("Capital of France?", card.getFront());
        assertEquals("Paris", card.getBack());
    }

    @Test
    public void testSetFront() {
        Flashcard card = new Flashcard("Q", "A");
        card.setFront("New Question");
        assertEquals("New Question", card.getFront());
    }

    @Test
    public void testSetBack() {
        Flashcard card = new Flashcard("Q", "A");
        card.setBack("New Answer");
        assertEquals("New Answer", card.getBack());
    }
}

package com.hemrajchauhan.flashcardapp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DeckTest {
    @Test
    public void testEmptyDeckConstructor() {
        Deck deck = new Deck("Test Deck");
        assertEquals("Test Deck", deck.getName());
        assertTrue(deck.getCards().isEmpty());
    }

    @Test
    public void testDeckConstructorWithCards() {
        Flashcard card1 = new Flashcard("Front1", "Back1");
        Flashcard card2 = new Flashcard("Front2", "Back2");
        List<Flashcard> cardList = Arrays.asList(card1, card2);

        Deck deck = new Deck("Quiz", cardList);
        assertEquals("Quiz", deck.getName());
        assertEquals(2, deck.getCards().size());
        assertEquals("Front1", deck.getCards().get(0).getFront());
    }

    @Test
    public void testAddCard() {
        Deck deck = new Deck("Math");
        Flashcard card = new Flashcard("2+2", "4");
        deck.addCard(card);
        assertEquals(1, deck.getCards().size());
        assertEquals(card, deck.getCards().get(0));
    }

    @Test
    public void testRemoveCard() {
        Flashcard card = new Flashcard("Question", "Answer");
        Deck deck = new Deck("RemoveTest", Arrays.asList(card));
        deck.removeCard(card);
        assertTrue(deck.getCards().isEmpty());
    }
}

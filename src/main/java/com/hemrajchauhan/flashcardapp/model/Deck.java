package com.hemrajchauhan.flashcardapp.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String name;
    private List<Flashcard> cards;

    // Constructor: creates empty deck
    public Deck(String name){
        this.name = name;
        this.cards = new ArrayList<>();
    }

    // Constructor: loads deck with cards
    public Deck(String name, List<Flashcard> cards){
        this.name = name;
        this.cards = new ArrayList<>(cards);
    }

    public String getName(){
        return name;
    }
    
    public List<Flashcard> getCards(){
        return cards;
    }

    public void addCard(Flashcard card){
        cards.add(card);
    }

    public void removeCard(Flashcard card){
        cards.remove(card);
    }
}

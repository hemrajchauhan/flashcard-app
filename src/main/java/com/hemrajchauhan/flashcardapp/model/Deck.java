package com.hemrajchauhan.flashcardapp.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String name;
    private List<Flashcard> cards;

    public Deck(String name){
        this.name = name;
        this.cards = new ArrayList<>();
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

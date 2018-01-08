package com.company.patterns;

import java.util.ArrayList;
import java.util.List;

public class DeckBuilder {

    private Integer deckSize = 0;
    private List<String> deckColors = new ArrayList<>();

    public DeckBuilder() {
    }

    public DeckBuilder setDeckSize(Integer size) {
        this.deckSize = size;
        return this;
    }

    public DeckBuilder setDeckColors(String color){
        this.deckColors.add(color);
        return this;
    }

    public DeckBuilder removeDeckColor(String color){
        if(this.deckColors.contains(color)){
            deckColors.remove(color);
        }
        return this;
    }

    public Integer getDeckSize() {
        return deckSize;
    }

    public List<String> getDeckColors() {
        return deckColors;
    }

    public Deck build(){
        return new Deck(this);
    }
}

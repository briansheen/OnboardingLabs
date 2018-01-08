package com.company.patterns;

import java.util.List;

public class Deck {
    private Integer deckSize;
    private List<String> deckColors;

    public Deck(DeckBuilder deckBuilder) {
        deckSize = deckBuilder.getDeckSize();
        deckColors = deckBuilder.getDeckColors();
    }

    public Integer getDeckSize() {
        return deckSize;
    }

    public void setDeckSize(Integer deckSize) {
        this.deckSize = deckSize;
    }

    public List<String> getDeckColors() {
        return deckColors;
    }

    public void setDeckColors(List<String> deckColors) {
        this.deckColors = deckColors;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deckSize=" + deckSize +
                ", deckColors=" + deckColors +
                '}';
    }
}

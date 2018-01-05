package com.company.patterns;

import java.util.HashMap;
import java.util.Map;

public class DeckList implements Cloneable {

    private Map<String, Map<String, Integer>> deck;
    private Map<String, Integer> creatureCards;
    private Map<String, Integer> planeswalkerCards;
    private Map<String, Integer> spellCards;
    private Map<String, Integer> landCards;

    public DeckList() {
        deck = new HashMap<>();
        creatureCards = new HashMap<>();
        planeswalkerCards = new HashMap<>();
        spellCards = new HashMap<>();
        landCards = new HashMap<>();
    }

    public DeckList(Map<String, Map<String, Integer>> deck) {
        this.deck = deck;
    }

    public Map<String, Map<String, Integer>> getDeck() {
        return deck;
    }

    public Map<String, Integer> getCreatureCards() {
        return creatureCards;
    }

    public Map<String, Integer> getPlaneswalkerCards() {
        return planeswalkerCards;
    }

    public Map<String, Integer> getSpellCards() {
        return spellCards;
    }

    public Map<String, Integer> getLandCards() {
        return landCards;
    }

    public void loadDeck() {
        creatureCards.put("Longtusk Cub", 4);
        creatureCards.put("Servant of the Conduit", 4);
        creatureCards.put("Rogue Refiner", 4);
        creatureCards.put("Whirler Virtuoso", 4);
        creatureCards.put("Bristling Hydra", 3);
        creatureCards.put("The Scarab God", 3);
        deck.put("Creatures", creatureCards);
        planeswalkerCards.put("Chandra, Torch of Defiance", 3);
        planeswalkerCards.put("Nicol Bolas, God-Pharaoh", 1);
        deck.put("Planeswalkers", planeswalkerCards);
        spellCards.put("Attune with Aether", 4);
        spellCards.put("Abrade", 2);
        spellCards.put("Harnessed Lightning", 4);
        spellCards.put("Confiscation Coup", 1);
        deck.put("Spells", spellCards);
        landCards.put("Aether Hub", 4);
        landCards.put("Botanical Sanctum", 4);
        landCards.put("Forest", 3);
        landCards.put("Island", 1);
        landCards.put("Mountain", 1);
        landCards.put("Rootbound Crag", 3);
        landCards.put("Sheltered Thicket", 2);
        landCards.put("Spirebluff Canal", 4);
        landCards.put("Swamp", 1);
        deck.put("Lands", landCards);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Map<String, Map<String, Integer>> temp = new HashMap<>();
        for (String s : this.getDeck().keySet()) {
            temp.put(s, this.getDeck().get(s));
        }
        return new DeckList(temp);
    }

    @Override
    public String toString() {
        return "DeckList{" +
                "deck=" + deck +
                ", creatureCards=" + creatureCards +
                ", planeswalkerCards=" + planeswalkerCards +
                ", spellCards=" + spellCards +
                ", landCards=" + landCards +
                '}';
    }
}

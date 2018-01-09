package com.company.patterns;

import java.util.HashMap;
import java.util.Map;

public class DeckList implements Cloneable {

    private Map<String, Integer> creatureCards;
    private Map<String, Integer> planeswalkerCards;
    private Map<String, Integer> spellCards;
    private Map<String, Integer> landCards;

    public DeckList() {
        creatureCards = new HashMap<>();
        planeswalkerCards = new HashMap<>();
        spellCards = new HashMap<>();
        landCards = new HashMap<>();
    }

    public DeckList(Map<String, Integer> creatures, Map<String, Integer> planeswalkers, Map<String, Integer> spells, Map<String, Integer> lands) {
        this.creatureCards = creatures;
        this.planeswalkerCards = planeswalkers;
        this.spellCards = spells;
        this.landCards = lands;
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
        planeswalkerCards.put("Chandra, Torch of Defiance", 3);
        planeswalkerCards.put("Nicol Bolas, God-Pharaoh", 1);
        spellCards.put("Attune with Aether", 4);
        spellCards.put("Abrade", 2);
        spellCards.put("Harnessed Lightning", 4);
        spellCards.put("Confiscation Coup", 1);
        landCards.put("Aether Hub", 4);
        landCards.put("Botanical Sanctum", 4);
        landCards.put("Forest", 3);
        landCards.put("Island", 1);
        landCards.put("Mountain", 1);
        landCards.put("Rootbound Crag", 3);
        landCards.put("Sheltered Thicket", 2);
        landCards.put("Spirebluff Canal", 4);
        landCards.put("Swamp", 1);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Map<String, Integer> tempCreatures = cloneHelper(this.getCreatureCards());
        Map<String, Integer> tempPlaneswalkers = cloneHelper(this.getPlaneswalkerCards());
        Map<String, Integer> tempSpells = cloneHelper(this.getSpellCards());
        Map<String, Integer> tempLands = cloneHelper(this.getLandCards());
        return new DeckList(tempCreatures, tempPlaneswalkers, tempSpells, tempLands);
    }

    private Map<String, Integer> cloneHelper(Map<String, Integer> cardMap){
        Map<String, Integer> newCardMap = new HashMap<>();
        for(String cardName : cardMap.keySet()){
            newCardMap.put(cardName, cardMap.get(cardName));
        }
        return newCardMap;
    }

    @Override
    public String toString() {
        return "DeckList{\n" +
                "creatureCards=" + creatureCards + "\n" +
                "planeswalkerCards=" + planeswalkerCards + "\n" +
                "spellCards=" + spellCards + "\n" +
                "landCards=" + landCards + "\n" +
                '}';
    }
}

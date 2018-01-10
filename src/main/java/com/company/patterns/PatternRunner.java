package com.company.patterns;

import java.util.Map;

public class PatternRunner {

    public static void main(String[] args) throws CloneNotSupportedException {
        DeckList temurEnergy = new DeckList();
        temurEnergy.loadDeck();

        System.out.println(temurEnergy);

        DeckList temurEnergyClone = (DeckList) temurEnergy.clone();

        Map<String, Integer> creatures = temurEnergyClone.getCreatureCards();
        creatures.put("Servant of the Conduit", 3);
        creatures.put("Vizier of Many Faces", 1);

        temurEnergyClone.getSpellCards().remove("Harnessed Lightning");
        temurEnergyClone.getSpellCards().put("Chandra's Defeat", 2);
        temurEnergyClone.getSpellCards().put("Negate", 2);

        System.out.println(temurEnergyClone + "\n");

        System.out.println("DeckBuilder");
        DeckBuilder db = new DeckBuilder();
        db.setDeckSize(60).setDeckColors("Blue").setDeckColors("Red");
        Deck deck = db.build();
        System.out.println(deck + "\n");

        System.out.println("Standard Game Factory");
        Game standardGame = GameFactory.getGame("standard");
        System.out.println(standardGame);
        standardGame.damageP1(2);
        System.out.println(standardGame + "\n");


        System.out.println("2HG Game Factory");
        Game twoHeadedGame = GameFactory.getGame("2hg");
        System.out.println(twoHeadedGame);
        twoHeadedGame.damageP2(4);
        System.out.println(twoHeadedGame + "\n");

        System.out.println("Abstract Standard Game Factory");
        Game standardGame2 = GameFactory.getGame(new StandardGameFactory());
        System.out.println(standardGame2);
        standardGame2.damageP1(1);
        System.out.println(standardGame2 + "\n");

        System.out.println("Abstract Custom Game Factory");
        Game customGame = GameFactory.getGame(new CustomGameFactory(40, 40));
        System.out.println(customGame);
        customGame.damageP1(3);
        System.out.println(customGame);
        customGame.damageP2(6);
        System.out.println(customGame);
    }
}

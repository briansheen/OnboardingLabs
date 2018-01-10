package com.company.patterns;

public class GameFactory {

    public static Game getGame(String gameType){
        if ("standard".equalsIgnoreCase(gameType)) {
            return new StandardGame();
        } else if("2hg".equalsIgnoreCase(gameType)) {
            return new TwoHeadedGiantGame();
        }
        return null;
    }

    public static Game getGame(GameAbstractFactory factory){
        return factory.createGame();
    }

}

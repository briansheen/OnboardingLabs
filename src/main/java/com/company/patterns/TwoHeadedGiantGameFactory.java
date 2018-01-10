package com.company.patterns;

public class TwoHeadedGiantGameFactory implements GameAbstractFactory{
    @Override
    public Game createGame() {
        return new TwoHeadedGiantGame();
    }
}

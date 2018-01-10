package com.company.patterns;

public class StandardGameFactory implements GameAbstractFactory{

    @Override
    public Game createGame() {
        return new StandardGame();
    }
}

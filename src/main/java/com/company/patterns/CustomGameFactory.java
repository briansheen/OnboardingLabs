package com.company.patterns;

public class CustomGameFactory implements GameAbstractFactory {

    private int p1Life;
    private int p2Life;

    public CustomGameFactory(int p1Life, int p2Life) {
        this.p1Life = p1Life;
        this.p2Life = p2Life;
    }

    @Override
    public Game createGame() {
        return new CustomGame(p1Life, p2Life);
    }
}

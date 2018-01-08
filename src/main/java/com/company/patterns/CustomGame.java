package com.company.patterns;

public class CustomGame extends Game {

    private int p1Life;
    private int p2Life;

    public CustomGame(int p1Life, int p2Life) {
        this.p1Life = p1Life;
        this.p2Life = p2Life;
    }

    @Override
    public int getP1Life() {
        return p1Life;
    }

    @Override
    public int getP2Life() {
        return p2Life;
    }

    @Override
    public void damageP1(int dmg) {
        p1Life -= dmg;

    }

    @Override
    public void damageP2(int dmg) {
        p2Life -= dmg;
    }
}

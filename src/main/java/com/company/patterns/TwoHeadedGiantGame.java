package com.company.patterns;

public class TwoHeadedGiantGame extends Game {

    private int p1Life;
    private int p2Life;

    public TwoHeadedGiantGame() {
        this.p1Life = 30;
        this.p2Life = 30;
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

package com.company.patterns;

public abstract class Game {
    public abstract int getP1Life();
    public abstract int getP2Life();
    public abstract void damageP1(int dmg);
    public abstract void damageP2(int dmg);

    @Override
    public String toString() {
        return "Player1 life = " + this.getP1Life() + ", Player2 life = " + this.getP2Life();
    }
}

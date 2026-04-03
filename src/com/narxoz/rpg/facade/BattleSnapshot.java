package com.narxoz.rpg.facade;

public class BattleSnapshot {
    private final boolean heroWon;
    private final int rounds;
    private final int heroHp;
    private final int bossHp;

    public BattleSnapshot(boolean heroWon, int rounds, int heroHp, int bossHp) {
        this.heroWon = heroWon;
        this.rounds = rounds;
        this.heroHp = heroHp;
        this.bossHp = bossHp;
    }

    public boolean heroWon() {
        return heroWon;
    }

    public int rounds() {
        return rounds;
    }

    public int heroHp() {
        return heroHp;
    }

    public int bossHp() {
        return bossHp;
    }
}

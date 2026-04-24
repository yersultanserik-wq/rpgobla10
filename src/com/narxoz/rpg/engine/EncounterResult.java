package com.narxoz.rpg.engine;

public class EncounterResult {
    private final boolean heroesWon;
    private final int rounds;
    private final String summary;

    public EncounterResult(boolean heroesWon, int rounds, String summary) {
        this.heroesWon = heroesWon;
        this.rounds = rounds;
        this.summary = summary;
    }

    public boolean heroesWon() { return heroesWon; }
    public int rounds() { return rounds; }
    public String summary() { return summary; }
}

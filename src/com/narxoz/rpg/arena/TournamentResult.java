package com.narxoz.rpg.arena;

public class TournamentResult {
    private final String heroName;
    private final String opponentName;
    private final String winner;
    private final int rounds;

    public TournamentResult(String heroName, String opponentName, String winner, int rounds) {
        this.heroName = heroName;
        this.opponentName = opponentName;
        this.winner = winner;
        this.rounds = rounds;
    }

    public String summary() {
        return "Tournament finished: " + heroName + " vs " + opponentName
                + " | winner=" + winner + " | rounds=" + rounds;
    }
}

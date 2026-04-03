package com.narxoz.rpg.facade;

public class AdventureResult {
    private final String dungeonName;
    private final String heroName;
    private final String bossName;
    private final boolean heroWon;
    private final int rounds;
    private final int heroHp;
    private final int bossHp;
    private final RewardBundle rewards;

    public AdventureResult(String dungeonName, String heroName, String bossName,
                           boolean heroWon, int rounds, int heroHp, int bossHp, RewardBundle rewards) {
        this.dungeonName = dungeonName;
        this.heroName = heroName;
        this.bossName = bossName;
        this.heroWon = heroWon;
        this.rounds = rounds;
        this.heroHp = heroHp;
        this.bossHp = bossHp;
        this.rewards = rewards;
    }

    public String summary() {
        return "Dungeon='" + dungeonName + "', hero='" + heroName + "', boss='" + bossName +
                "', winner='" + (heroWon ? heroName : bossName) + "', rounds=" + rounds +
                ", heroHp=" + heroHp + ", bossHp=" + bossHp + ", gold=" + rewards.gold() +
                ", exp=" + rewards.experience() + ", item='" + rewards.item() + "'";
    }
}

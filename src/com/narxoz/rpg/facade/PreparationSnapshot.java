package com.narxoz.rpg.facade;

public class PreparationSnapshot {
    private final String dungeonName;
    private final String heroName;
    private final String actionLabel;
    private final int moraleBoost;

    public PreparationSnapshot(String dungeonName, String heroName, String actionLabel, int moraleBoost) {
        this.dungeonName = dungeonName;
        this.heroName = heroName;
        this.actionLabel = actionLabel;
        this.moraleBoost = moraleBoost;
    }

    public String dungeonName() {
        return dungeonName;
    }

    public String heroName() {
        return heroName;
    }

    public String actionLabel() {
        return actionLabel;
    }

    public int moraleBoost() {
        return moraleBoost;
    }
}

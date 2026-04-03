package com.narxoz.rpg.facade;

public class RewardBundle {
    private final int gold;
    private final int experience;
    private final String item;

    public RewardBundle(int gold, int experience, String item) {
        this.gold = gold;
        this.experience = experience;
        this.item = item;
    }

    public int gold() {
        return gold;
    }

    public int experience() {
        return experience;
    }

    public String item() {
        return item;
    }
}

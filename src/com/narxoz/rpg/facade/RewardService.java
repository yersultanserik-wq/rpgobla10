package com.narxoz.rpg.facade;

public class RewardService {
    public RewardBundle grantRewards(String bossName, boolean heroWon, int rounds) {
        if (!heroWon) {
            System.out.println("[Rewards] No rewards granted. The dungeon was not cleared.");
            return new RewardBundle(0, 0, "None");
        }

        int gold = 100 + Math.max(0, 6 - rounds) * 20;
        int experience = 150 + Math.max(0, 6 - rounds) * 25;
        String item = "Trophy from " + bossName;

        System.out.println("[Rewards] Gold awarded: " + gold);
        System.out.println("[Rewards] Experience awarded: " + experience);
        System.out.println("[Rewards] Rare drop: " + item);
        return new RewardBundle(gold, experience, item);
    }
}

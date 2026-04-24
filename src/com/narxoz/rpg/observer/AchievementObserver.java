package com.narxoz.rpg.observer;

public class AchievementObserver implements GameObserver {
    @Override public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.BOSS_PHASE_CHANGED) {
            System.out.println("ACHIEVEMENT: Phase breaker unlocked — " + event.getMessage());
        }
        if (event.getType() == GameEventType.BOSS_DEFEATED) {
            System.out.println("ACHIEVEMENT: Dungeon conqueror unlocked.");
        }
    }
}

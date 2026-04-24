package com.narxoz.rpg.observer;

import com.narxoz.rpg.combatant.Hero;

public class PartySupportObserver implements GameObserver {
    private final Hero hero;

    public PartySupportObserver(Hero hero) {
        this.hero = hero;
    }

    @Override public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.HERO_LOW_HP && hero.getName().equals(event.getTarget()) && hero.isAlive()) {
            hero.heal(10);
            System.out.println("SUPPORT: " + hero.getName() + " receives emergency healing (+10 HP).");
        }
    }
}

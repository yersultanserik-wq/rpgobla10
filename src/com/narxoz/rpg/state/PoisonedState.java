package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class PoisonedState implements HeroState {
    private int turnsLeft;

    public PoisonedState(int turnsLeft) {
        this.turnsLeft = turnsLeft;
    }

    @Override public String name() { return "Poisoned(" + turnsLeft + ")"; }
    @Override public int modifyDamageDealt(Hero hero, int damage) { return Math.max(1, damage - 3); }
    @Override public int modifyDamageReceived(Hero hero, int damage) { return damage + 2; }
    @Override public HeroState nextState(Hero hero) {
        turnsLeft--;
        if (turnsLeft <= 0) return new NormalState();
        return this;
    }
}

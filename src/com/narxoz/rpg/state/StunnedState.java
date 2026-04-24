package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class StunnedState implements HeroState {
    private int turnsLeft;

    public StunnedState(int turnsLeft) {
        this.turnsLeft = turnsLeft;
    }

    @Override public String name() { return "Stunned(" + turnsLeft + ")"; }
    @Override public int modifyDamageDealt(Hero hero, int damage) { return 0; }
    @Override public int modifyDamageReceived(Hero hero, int damage) { return damage; }
    @Override public HeroState nextState(Hero hero) {
        turnsLeft--;
        if (turnsLeft <= 0) return new NormalState();
        return this;
    }
}

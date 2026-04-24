package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class RageState implements HeroState {
    @Override public String name() { return "Rage"; }
    @Override public int modifyDamageDealt(Hero hero, int damage) { return damage + 8; }
    @Override public int modifyDamageReceived(Hero hero, int damage) { return damage + 1; }
    @Override public HeroState nextState(Hero hero) {
        if (hero.hpPercent() > 45) return new NormalState();
        return this;
    }
}

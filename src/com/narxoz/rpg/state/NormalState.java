package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class NormalState implements HeroState {
    @Override public String name() { return "Normal"; }
    @Override public int modifyDamageDealt(Hero hero, int damage) { return damage; }
    @Override public int modifyDamageReceived(Hero hero, int damage) { return damage; }
    @Override public HeroState nextState(Hero hero) {
        if (hero.hpPercent() <= 35) return new RageState();
        return this;
    }
}

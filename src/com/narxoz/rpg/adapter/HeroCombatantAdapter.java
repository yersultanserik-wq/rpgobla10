package com.narxoz.rpg.adapter;

import com.narxoz.rpg.hero.Hero;

public class HeroCombatantAdapter implements Combatant {
    private final Hero hero;

    public HeroCombatantAdapter(Hero hero) {
        this.hero = hero;
    }

    @Override public String name() { return hero.getHeroClass() + " " + hero.getName(); }
    @Override public int hp() { return hero.getHp(); }
    @Override public boolean alive() { return hero.isAlive(); }
    @Override public int dealDamage() { return hero.attack(); }
    @Override public void takeDamage(int raw) { hero.takeDamage(raw); }
}
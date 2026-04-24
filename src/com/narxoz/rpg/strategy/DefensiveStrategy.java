package com.narxoz.rpg.strategy;

public class DefensiveStrategy implements CombatStrategy {
    @Override public String name() { return "Defensive"; }
    @Override public int calculateDamage(int baseAttack) { return Math.max(1, baseAttack - 2); }
    @Override public int calculateDefense(int baseDefense) { return baseDefense + 5; }
}

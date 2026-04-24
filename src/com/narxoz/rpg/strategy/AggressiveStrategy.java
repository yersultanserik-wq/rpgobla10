package com.narxoz.rpg.strategy;

public class AggressiveStrategy implements CombatStrategy {
    @Override public String name() { return "Aggressive"; }
    @Override public int calculateDamage(int baseAttack) { return baseAttack + 6; }
    @Override public int calculateDefense(int baseDefense) { return Math.max(0, baseDefense - 2); }
}

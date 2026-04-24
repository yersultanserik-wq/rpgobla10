package com.narxoz.rpg.strategy;

public class EnragedStrategy implements CombatStrategy {
    @Override public String name() { return "Enraged"; }
    @Override public int calculateDamage(int baseAttack) { return baseAttack + 12; }
    @Override public int calculateDefense(int baseDefense) { return Math.max(0, baseDefense - 4); }
}

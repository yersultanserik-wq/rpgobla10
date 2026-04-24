package com.narxoz.rpg.strategy;

public class BalancedStrategy implements CombatStrategy {
    @Override public String name() { return "Balanced"; }
    @Override public int calculateDamage(int baseAttack) { return baseAttack + 2; }
    @Override public int calculateDefense(int baseDefense) { return baseDefense + 1; }
}

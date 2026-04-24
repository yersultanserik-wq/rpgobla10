package com.narxoz.rpg.strategy;

public interface CombatStrategy {
    String name();
    int calculateDamage(int baseAttack);
    int calculateDefense(int baseDefense);
}

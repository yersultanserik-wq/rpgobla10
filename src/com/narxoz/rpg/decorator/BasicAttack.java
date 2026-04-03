package com.narxoz.rpg.decorator;

import com.narxoz.rpg.adapter.Combatant;

public class BasicAttack extends AttackAction {
    private final String name;
    private final int flatBonus;

    public BasicAttack(String name, int flatBonus) {
        this.name = name;
        this.flatBonus = flatBonus;
    }

    @Override
    public String describe() {
        return name;
    }

    @Override
    int rawDamage(Combatant attacker, Combatant target) {
        return attacker.dealDamage() + flatBonus;
    }

    @Override
    String effectBreakdown() {
        return "Base attack power + bonus " + flatBonus;
    }
}

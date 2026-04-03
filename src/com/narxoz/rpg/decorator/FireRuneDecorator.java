package com.narxoz.rpg.decorator;

import com.narxoz.rpg.adapter.Combatant;

public class FireRuneDecorator extends ActionDecorator {
    private static final int FIRE_BONUS = 4;

    public FireRuneDecorator(AttackAction wrapped) {
        super(wrapped);
    }

    @Override
    public String describe() {
        return wrapped.describe() + " + Fire Rune";
    }

    @Override
    int rawDamage(Combatant attacker, Combatant target) {
        return wrapped.rawDamage(attacker, target) + FIRE_BONUS;
    }

    @Override
    String effectBreakdown() {
        return wrapped.effectBreakdown() + " | Fire Rune (+" + FIRE_BONUS + ")";
    }
}

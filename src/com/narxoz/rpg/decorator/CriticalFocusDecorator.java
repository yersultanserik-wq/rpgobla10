package com.narxoz.rpg.decorator;

import com.narxoz.rpg.adapter.Combatant;

public class CriticalFocusDecorator extends ActionDecorator {
    public CriticalFocusDecorator(AttackAction wrapped) {
        super(wrapped);
    }

    @Override
    public String describe() {
        return wrapped.describe() + " + Critical Focus";
    }

    @Override
    int rawDamage(Combatant attacker, Combatant target) {
        return (int) Math.ceil(wrapped.rawDamage(attacker, target) * 1.5);
    }

    @Override
    String effectBreakdown() {
        return wrapped.effectBreakdown() + " | Critical Focus (x1.5)";
    }
}

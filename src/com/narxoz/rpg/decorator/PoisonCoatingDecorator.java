package com.narxoz.rpg.decorator;

import com.narxoz.rpg.adapter.Combatant;

public class PoisonCoatingDecorator extends ActionDecorator {
    private static final int POISON_BONUS = 2;

    public PoisonCoatingDecorator(AttackAction wrapped) {
        super(wrapped);
    }

    @Override
    public String describe() {
        return wrapped.describe() + " + Poison Coating";
    }

    @Override
    int rawDamage(Combatant attacker, Combatant target) {
        return wrapped.rawDamage(attacker, target) + POISON_BONUS;
    }

    @Override
    String effectBreakdown() {
        return wrapped.effectBreakdown() + " | Poison Coating (+" + POISON_BONUS + ")";
    }
}

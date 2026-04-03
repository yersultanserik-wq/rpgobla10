package com.narxoz.rpg.decorator;

import com.narxoz.rpg.adapter.Combatant;

public abstract class AttackAction {
    public abstract String describe();

    abstract int rawDamage(Combatant attacker, Combatant target);

    abstract String effectBreakdown();

    public int execute(Combatant attacker, Combatant target) {
        int before = target.hp();
        int raw = Math.max(0, rawDamage(attacker, target));

        System.out.println(attacker.name() + " uses " + describe() + " on " + target.name());
        System.out.println("Effects: " + effectBreakdown());
        System.out.println("Raw damage: " + raw);

        target.takeDamage(raw);
        System.out.println(target.name() + " HP: " + before + " -> " + target.hp());
        return raw;
    }
}

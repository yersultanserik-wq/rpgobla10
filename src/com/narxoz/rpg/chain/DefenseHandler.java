package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public abstract class DefenseHandler {
    private DefenseHandler next;

    public DefenseHandler setNext(DefenseHandler next) {
        this.next = next;
        return next;
    }

    public final int handle(int incomingDamage, ArenaFighter defender) {
        int remaining = apply(incomingDamage, defender);
        if (remaining <= 0) {
            return 0;
        }
        if (next != null) {
            return next.handle(remaining, defender);
        }
        return remaining;
    }

    protected abstract int apply(int incomingDamage, ArenaFighter defender);
}

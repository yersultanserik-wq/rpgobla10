package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class BlockHandler extends DefenseHandler {
    @Override
    protected int apply(int incomingDamage, ArenaFighter defender) {
        int blocked = defender.consumeBlockPoints();
        int remaining = Math.max(0, incomingDamage - blocked);
        if (blocked > 0) {
            System.out.println("Defense chain: " + defender.getName() + " blocks " + blocked + " damage.");
        }
        return remaining;
    }
}

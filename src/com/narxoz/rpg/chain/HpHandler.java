package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class HpHandler extends DefenseHandler {
    @Override
    protected int apply(int incomingDamage, ArenaFighter defender) {
        defender.applyDirectDamage(incomingDamage);
        System.out.println("Defense chain: " + defender.getName() + " loses " + incomingDamage + " HP.");
        return 0;
    }
}

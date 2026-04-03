package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class ArmorHandler extends DefenseHandler {
    @Override
    protected int apply(int incomingDamage, ArenaFighter defender) {
        int armor = defender.consumeArmorPoints();
        int remaining = Math.max(0, incomingDamage - armor);
        System.out.println("Defense chain: armor absorbs " + Math.min(incomingDamage, armor) + " damage.");
        return remaining;
    }
}

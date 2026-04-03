package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class DodgeHandler extends DefenseHandler {
    @Override
    protected int apply(int incomingDamage, ArenaFighter defender) {
        if (defender.consumeDodge()) {
            System.out.println("Defense chain: " + defender.getName() + " dodged the attack.");
            return 0;
        }
        return incomingDamage;
    }
}

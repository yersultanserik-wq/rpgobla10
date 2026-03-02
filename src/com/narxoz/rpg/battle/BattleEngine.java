package com.narxoz.rpg.battle;

import com.narxoz.rpg.adapter.Combatant;

public class BattleEngine {
    private static BattleEngine instance;

    private BattleEngine() {}

    public static BattleEngine getInstance() {
        if (instance == null) instance = new BattleEngine();
        return instance;
    }

    public void battle(Combatant a, Combatant b) {
        System.out.println("\n=== BATTLE START ===");
        System.out.println(a.name() + " vs " + b.name());

        int round = 1;
        while (a.alive() && b.alive()) {
            System.out.println("\n-- Round " + round + " --");

            hit(a, b);
            if (!b.alive()) break;

            hit(b, a);

            System.out.println("Status: " + a.name() + " HP=" + a.hp() + " | " + b.name() + " HP=" + b.hp());
            round++;
        }

        System.out.println("\n=== RESULT ===");
        if (a.alive()) System.out.println("Winner: " + a.name());
        else System.out.println("Winner: " + b.name());
        System.out.println("=== BATTLE END ===\n");
    }

    private void hit(Combatant attacker, Combatant target) {
        int dmg = attacker.dealDamage();
        System.out.println(attacker.name() + " hits for " + dmg);
        target.takeDamage(dmg);
    }
}
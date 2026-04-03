package com.narxoz.rpg.facade;

import com.narxoz.rpg.adapter.Combatant;
import com.narxoz.rpg.decorator.AttackAction;

public class BattleService {
    public BattleSnapshot fight(Combatant hero, Combatant boss, AttackAction action) {
        System.out.println("\n[Battle] " + hero.name() + " faces " + boss.name());
        int round = 1;
        while (hero.alive() && boss.alive()) {
            System.out.println("\n[Battle] Round " + round);
            action.execute(hero, boss);
            if (!boss.alive()) {
                break;
            }

            int bossRaw = boss.dealDamage();
            int heroBefore = hero.hp();
            System.out.println(boss.name() + " strikes back for raw damage: " + bossRaw);
            hero.takeDamage(bossRaw);
            System.out.println(hero.name() + " HP: " + heroBefore + " -> " + hero.hp());
            round++;
        }

        int completedRounds = hero.alive() && !boss.alive() ? round : round;
        boolean heroWon = hero.alive() && !boss.alive();
        System.out.println("\n[Battle] Winner: " + (heroWon ? hero.name() : boss.name()));
        return new BattleSnapshot(heroWon, completedRounds, hero.hp(), boss.hp());
    }
}

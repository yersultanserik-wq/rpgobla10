package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;

import java.util.List;

public class MonsterFloor extends TowerFloor {
    private Monster monster;

    public MonsterFloor(int number) {
        super(number, "Monster Hall");
    }

    @Override protected void setup(List<Hero> party) {
        monster = new Monster("Haunted Guard", 38, 10, 2);
        System.out.println("A monster appears: " + monster);
    }

    @Override protected FloorResult resolveChallenge(List<Hero> party) {
        for (Hero hero : party) {
            if (!hero.isAlive() || !monster.isAlive()) continue;
            int damage = hero.attack();
            monster.takeDamage(damage);
            System.out.println(hero.getName() + " attacks for " + damage + ". " + monster);
        }
        if (monster.isAlive()) {
            Hero target = firstAlive(party);
            if (target != null) {
                target.takeDamage(monster.attack());
                System.out.println(monster.getName() + " counterattacks. " + target);
            }
        }
        return new FloorResult(!monster.isAlive(), monster.isAlive() ? "Monster still stands." : "Monster defeated.");
    }

    @Override protected void awardLoot(List<Hero> party) {
        for (Hero hero : party) if (hero.isAlive()) hero.heal(5);
        System.out.println("Loot: party receives small healing herbs (+5 HP). ");
    }

    private Hero firstAlive(List<Hero> party) {
        for (Hero hero : party) if (hero.isAlive()) return hero;
        return null;
    }
}

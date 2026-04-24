package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.PoisonedState;

import java.util.List;

public class TrapFloor extends TowerFloor {
    public TrapFloor(int number) {
        super(number, "Venom Trap Room");
    }

    @Override protected void setup(List<Hero> party) {
        System.out.println("The floor is covered with cursed poison runes.");
    }

    @Override protected FloorResult resolveChallenge(List<Hero> party) {
        for (Hero hero : party) {
            if (!hero.isAlive()) continue;
            hero.takeDamage(8);
            hero.setState(new PoisonedState(2));
            System.out.println(hero.getName() + " triggers venom. " + hero);
        }
        return new FloorResult(true, "The party survives the trap.");
    }

    @Override protected void awardLoot(List<Hero> party) {
        System.out.println("Loot: antidote knowledge gained, but poison still needs turns to wear off.");
    }
}

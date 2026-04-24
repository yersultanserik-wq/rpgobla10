package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;

import java.util.List;

public abstract class TowerFloor {
    private final int number;
    private final String title;

    protected TowerFloor(int number, String title) {
        this.number = number;
        this.title = title;
    }

    public final FloorResult explore(List<Hero> party) {
        announce();
        setup(party);
        beforeChallenge(party);
        FloorResult result = resolveChallenge(party);
        if (result.cleared()) {
            awardLoot(party);
        }
        cleanup(party);
        return result;
    }

    protected void announce() {
        System.out.println("\n== Floor " + number + ": " + title + " ==");
    }

    protected abstract void setup(List<Hero> party);
    protected void beforeChallenge(List<Hero> party) { }
    protected abstract FloorResult resolveChallenge(List<Hero> party);
    protected abstract void awardLoot(List<Hero> party);

    protected void cleanup(List<Hero> party) {
        for (Hero hero : party) {
            hero.tickState();
        }
        System.out.println("Floor cleanup complete. States advanced.");
    }
}

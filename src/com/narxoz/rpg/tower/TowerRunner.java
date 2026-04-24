package com.narxoz.rpg.tower;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;

import java.util.List;

public class TowerRunner {
    public TowerRunResult run(List<Hero> party, List<TowerFloor> floors) {
        int cleared = 0;
        for (TowerFloor floor : floors) {
            if (!anyAlive(party)) {
                return new TowerRunResult(false, cleared, "Party fell before reaching the next floor.");
            }
            FloorResult result = floor.explore(party);
            System.out.println("Floor result: " + result.message());
            if (!result.cleared()) {
                return new TowerRunResult(false, cleared, "Tower run stopped: " + result.message());
            }
            cleared++;
        }
        return new TowerRunResult(true, cleared, "Party cleared the haunted tower.");
    }

    private boolean anyAlive(List<Hero> party) {
        for (Hero hero : party) if (hero.isAlive()) return true;
        return false;
    }
}

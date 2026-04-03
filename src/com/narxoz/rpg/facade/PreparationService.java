package com.narxoz.rpg.facade;

import com.narxoz.rpg.adapter.Combatant;
import com.narxoz.rpg.decorator.AttackAction;

public class PreparationService {
    public PreparationSnapshot prepare(String dungeonName, Combatant hero, AttackAction action) {
        System.out.println("[Preparation] Entering dungeon: " + dungeonName);
        System.out.println("[Preparation] Hero: " + hero.name());
        System.out.println("[Preparation] Selected attack: " + action.describe());
        System.out.println("[Preparation] Supplies checked. Morale boost applied: +1 confidence");
        return new PreparationSnapshot(dungeonName, hero.name(), action.describe(), 1);
    }
}

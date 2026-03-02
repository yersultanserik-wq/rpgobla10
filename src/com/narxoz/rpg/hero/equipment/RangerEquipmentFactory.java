package com.narxoz.rpg.hero.equipment;

public class RangerEquipmentFactory implements EquipmentFactory {
    @Override public Weapon createWeapon() {
        return new Weapon() {
            public String name() { return "Longbow"; }
            public int attackBonus() { return 6; }
        };
    }
    @Override public Armor createArmor() {
        return new Armor() {
            public String name() { return "Leather Armor"; }
            public int defenseBonus() { return 2; }
        };
    }
    @Override public String theme() { return "Ranger"; }
}

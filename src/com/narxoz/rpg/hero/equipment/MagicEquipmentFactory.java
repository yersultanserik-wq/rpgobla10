package com.narxoz.rpg.hero.equipment;

public class MagicEquipmentFactory implements EquipmentFactory {
    @Override public Weapon createWeapon() {
        return new Weapon() {
            public String name() { return "Arcane Staff"; }
            public int attackBonus() { return 7; }
        };
    }
    @Override public Armor createArmor() {
        return new Armor() {
            public String name() { return "Mystic Robe"; }
            public int defenseBonus() { return 2; }
        };
    }
    @Override public String theme() { return "Magic"; }
}
package com.narxoz.rpg.hero.equipment;

public class MedievalEquipmentFactory implements EquipmentFactory {
    @Override public Weapon createWeapon() {
        return new Weapon() {
            public String name() { return "Steel Sword"; }
            public int attackBonus() { return 5; }
        };
    }
    @Override public Armor createArmor() {
        return new Armor() {
            public String name() { return "Chainmail"; }
            public int defenseBonus() { return 3; }
        };
    }
    @Override public String theme() { return "Medieval"; }
}
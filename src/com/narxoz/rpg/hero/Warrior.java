package com.narxoz.rpg.hero;

public class Warrior extends Hero {
    public Warrior(String name) {
        super(name, HeroClass.WARRIOR, 55, 10, 4);
    }
    @Override protected int specialAbilityBonus() { return 2; } // ярость
}
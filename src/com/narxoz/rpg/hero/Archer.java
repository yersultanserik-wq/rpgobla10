package com.narxoz.rpg.hero;

public class Archer extends Hero {
    public Archer(String name) {
        super(name, HeroClass.ARCHER, 45, 11, 3);
    }
    @Override protected int specialAbilityBonus() { return 3; } // точный выстрел
}
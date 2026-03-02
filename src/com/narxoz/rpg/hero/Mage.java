package com.narxoz.rpg.hero;

public class Mage extends Hero {
    public Mage(String name) {
        super(name, HeroClass.MAGE, 40, 12, 2);
    }
    @Override protected int specialAbilityBonus() { return 4; } // заклинание
}
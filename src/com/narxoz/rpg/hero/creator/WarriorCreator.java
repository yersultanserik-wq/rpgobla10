package com.narxoz.rpg.hero.creator;

import com.narxoz.rpg.hero.Hero;
import com.narxoz.rpg.hero.Warrior;

public class WarriorCreator extends HeroCreator {
    @Override protected Hero createHero(String name) { return new Warrior(name); }
}
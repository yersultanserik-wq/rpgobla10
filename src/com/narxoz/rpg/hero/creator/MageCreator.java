package com.narxoz.rpg.hero.creator;

import com.narxoz.rpg.hero.Hero;
import com.narxoz.rpg.hero.Mage;

public class MageCreator extends HeroCreator {
    @Override protected Hero createHero(String name) { return new Mage(name); }
}
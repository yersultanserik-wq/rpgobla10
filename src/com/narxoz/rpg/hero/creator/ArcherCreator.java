package com.narxoz.rpg.hero.creator;

import com.narxoz.rpg.hero.Archer;
import com.narxoz.rpg.hero.Hero;

public class ArcherCreator extends HeroCreator {
    @Override protected Hero createHero(String name) { return new Archer(name); }
}
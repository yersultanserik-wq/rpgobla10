package com.narxoz.rpg.hero.creator;

import com.narxoz.rpg.hero.Hero;

public abstract class HeroCreator {
    public final Hero create(String name) {
        // Тут можно добавить общую логику (логирование/валидация)
        return createHero(name);
    }

    protected abstract Hero createHero(String name);
}
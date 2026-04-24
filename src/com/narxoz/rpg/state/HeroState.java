package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public interface HeroState {
    String name();
    int modifyDamageDealt(Hero hero, int damage);
    int modifyDamageReceived(Hero hero, int damage);
    HeroState nextState(Hero hero);
}

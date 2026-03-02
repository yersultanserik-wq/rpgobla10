package com.narxoz.rpg.adapter;

public interface Combatant {
    String name();
    int hp();
    boolean alive();
    int dealDamage();          // сколько урона наносит
    void takeDamage(int raw);  // получить урон
}
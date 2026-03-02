package com.narxoz.rpg.adapter;

import com.narxoz.rpg.enemy.Enemy;

public class EnemyCombatantAdapter implements Combatant {
    private final Enemy enemy;

    public EnemyCombatantAdapter(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override public String name() { return enemy.title(); }
    @Override public int hp() { return enemy.vitality(); }
    @Override public boolean alive() { return enemy.alive(); }
    @Override public int dealDamage() { return enemy.strike(); }
    @Override public void takeDamage(int raw) { enemy.receive(raw); }
}
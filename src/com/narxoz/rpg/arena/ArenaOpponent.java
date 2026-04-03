package com.narxoz.rpg.arena;

import com.narxoz.rpg.enemy.Enemy;

public class ArenaOpponent {
    private final String title;
    private final int attackPower;
    private int hp;

    public ArenaOpponent(Enemy enemy) {
        this.title = enemy.title();
        this.hp = enemy.vitality();
        this.attackPower = enemy.strike();
    }

    public String getTitle() {
        return title;
    }

    public int getHp() {
        return hp;
    }

    public int attack() {
        return attackPower;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int amount) {
        hp = Math.max(0, hp - Math.max(0, amount));
    }

    public void restoreHp(int value) {
        hp = Math.max(0, value);
    }

    @Override
    public String toString() {
        return "Opponent " + title + " [HP=" + hp + ", ATK=" + attackPower + "]";
    }
}

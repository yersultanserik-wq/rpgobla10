package com.narxoz.rpg.combatant;

public class Monster {
    private final String name;
    private int hp;
    private final int attack;
    private final int defense;

    public Monster(String name, int hp, int attack, int defense) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public boolean isAlive() { return hp > 0; }
    public int attack() { return attack; }

    public void takeDamage(int rawDamage) {
        hp = Math.max(0, hp - Math.max(0, rawDamage - defense));
    }

    @Override public String toString() {
        return name + " [HP=" + hp + ", ATK=" + attack + ", DEF=" + defense + "]";
    }
}

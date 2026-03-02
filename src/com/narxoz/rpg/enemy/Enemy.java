package com.narxoz.rpg.enemy;

public class Enemy implements Cloneable {
    private String title;
    private int vitality;   // HP
    private int power;      // ATK
    private int armor;      // DEF

    Enemy(String title, int vitality, int power, int armor) {
        this.title = title;
        this.vitality = vitality;
        this.power = power;
        this.armor = armor;
    }

    public String title() { return title; }
    public int vitality() { return vitality; }
    public boolean alive() { return vitality > 0; }

    // другая семантика атаки
    public int strike() { return power; }

    // другая семантика получения урона
    public void receive(int raw) {
        int reduced = Math.max(0, raw - armor);
        vitality = Math.max(0, vitality - reduced);
    }

    public int armor() { return armor; }

    @Override
    public Enemy clone() {
        try {
            return (Enemy) super.clone();
        } catch (CloneNotSupportedException e) {
            // fallback (по факту не должен происходить)
            return new Enemy(title, vitality, power, armor);
        }
    }

    @Override
    public String toString() {
        return "Enemy " + title + " [HP=" + vitality + ", ATK=" + power + ", DEF=" + armor + "]";
    }
}
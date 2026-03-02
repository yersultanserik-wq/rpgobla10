package com.narxoz.rpg.enemy;

public class EnemyBuilder {
    private String title = "Unknown";
    private int vitality = 30;
    private int power = 8;
    private int armor = 1;

    public EnemyBuilder title(String title) { this.title = title; return this; }
    public EnemyBuilder vitality(int vitality) { this.vitality = vitality; return this; }
    public EnemyBuilder power(int power) { this.power = power; return this; }
    public EnemyBuilder armor(int armor) { this.armor = armor; return this; }

    public Enemy build() {
        if (vitality <= 0) throw new IllegalArgumentException("vitality must be > 0");
        if (power < 0) throw new IllegalArgumentException("power must be >= 0");
        if (armor < 0) throw new IllegalArgumentException("armor must be >= 0");
        return new Enemy(title, vitality, power, armor);
    }
}
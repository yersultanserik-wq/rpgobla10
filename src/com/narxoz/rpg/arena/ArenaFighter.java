package com.narxoz.rpg.arena;

import com.narxoz.rpg.hero.Hero;

public class ArenaFighter {
    private final String name;
    private final String heroClass;
    private final int maxHp;
    private final int attackPower;
    private final int baseArmor;

    private int hp;
    private boolean dodgeReady;
    private int blockPoints;
    private int temporaryArmor;

    public ArenaFighter(Hero hero) {
        this.name = hero.getName();
        this.heroClass = hero.getHeroClass().name();
        this.maxHp = hero.getHp();
        this.hp = hero.getHp();
        this.attackPower = hero.attack();
        this.baseArmor = hero.getDefensePower();
    }

    public String getName() {
        return name;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int attack() {
        return attackPower;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void heal(int amount) {
        if (amount <= 0 || !isAlive()) {
            return;
        }
        hp = Math.min(maxHp, hp + amount);
    }

    public void restoreHealth(int newHp) {
        hp = Math.max(0, Math.min(maxHp, newHp));
    }

    public void prepareDefense() {
        dodgeReady = "ARCHER".equals(heroClass) && hp <= (maxHp - 8);
        blockPoints = "WARRIOR".equals(heroClass) ? 6 : 3;
        temporaryArmor = "MAGE".equals(heroClass) ? 5 : 2;
    }

    public void clearPreparedDefense() {
        dodgeReady = false;
        blockPoints = 0;
        temporaryArmor = 0;
    }

    public boolean consumeDodge() {
        boolean result = dodgeReady;
        dodgeReady = false;
        return result;
    }

    public int consumeBlockPoints() {
        int value = blockPoints;
        blockPoints = 0;
        return value;
    }

    public int consumeArmorPoints() {
        int value = baseArmor + temporaryArmor;
        temporaryArmor = 0;
        return value;
    }

    public void applyDirectDamage(int amount) {
        hp = Math.max(0, hp - Math.max(0, amount));
    }

    @Override
    public String toString() {
        return heroClass + " " + name + " [HP=" + hp + "/" + maxHp + ", ATK=" + attackPower + ", ARMOR=" + baseArmor + "]";
    }
}

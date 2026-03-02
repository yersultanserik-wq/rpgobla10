package com.narxoz.rpg.hero;

import com.narxoz.rpg.hero.equipment.Armor;
import com.narxoz.rpg.hero.equipment.EquipmentFactory;
import com.narxoz.rpg.hero.equipment.Weapon;

public abstract class Hero {
    private final String name;
    private final HeroClass heroClass;

    private int hp;
    private int baseAttack;
    private int baseDefense;

    private Weapon weapon;
    private Armor armor;

    protected Hero(String name, HeroClass heroClass, int hp, int baseAttack, int baseDefense) {
        this.name = name;
        this.heroClass = heroClass;
        this.hp = hp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    public String getName() { return name; }
    public HeroClass getHeroClass() { return heroClass; }
    public int getHp() { return hp; }
    public boolean isAlive() { return hp > 0; }

    public void equip(EquipmentFactory factory) {
        this.weapon = factory.createWeapon();
        this.armor = factory.createArmor();
    }

    public int getAttackPower() {
        return baseAttack + (weapon == null ? 0 : weapon.attackBonus());
    }

    public int getDefensePower() {
        return baseDefense + (armor == null ? 0 : armor.defenseBonus());
    }

    public void takeDamage(int rawDamage) {
        int reduced = Math.max(0, rawDamage - getDefensePower());
        hp = Math.max(0, hp - reduced);
    }

    public int attack() {
        return getAttackPower() + specialAbilityBonus();
    }

    protected abstract int specialAbilityBonus();

    public String equipmentInfo() {
        String w = (weapon == null) ? "None" : weapon.name();
        String a = (armor == null) ? "None" : armor.name();
        return "Weapon=" + w + ", Armor=" + a;
    }

    @Override
    public String toString() {
        return heroClass + " " + name + " [HP=" + hp + ", ATK=" + getAttackPower() + ", DEF=" + getDefensePower() + ", " + equipmentInfo() + "]";
    }
}
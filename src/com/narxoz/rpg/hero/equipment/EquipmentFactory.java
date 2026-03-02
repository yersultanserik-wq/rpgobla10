package com.narxoz.rpg.hero.equipment;

public interface EquipmentFactory {
    Weapon createWeapon();
    Armor createArmor();
    String theme();
}
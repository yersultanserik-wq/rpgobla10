package com.narxoz.rpg;

import com.narxoz.rpg.adapter.Combatant;
import com.narxoz.rpg.adapter.EnemyCombatantAdapter;
import com.narxoz.rpg.adapter.HeroCombatantAdapter;
import com.narxoz.rpg.battle.BattleEngine;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.EnemyBuilder;
import com.narxoz.rpg.enemy.EnemyCatalog;
import com.narxoz.rpg.hero.Hero;
import com.narxoz.rpg.hero.creator.ArcherCreator;
import com.narxoz.rpg.hero.creator.HeroCreator;
import com.narxoz.rpg.hero.creator.MageCreator;
import com.narxoz.rpg.hero.creator.WarriorCreator;
import com.narxoz.rpg.hero.equipment.MagicEquipmentFactory;
import com.narxoz.rpg.hero.equipment.MedievalEquipmentFactory;
import com.narxoz.rpg.hero.equipment.RangerEquipmentFactory;

public class Main {
    public static void main(String[] args) {


        HeroCreator creator = new WarriorCreator();
        Hero hero = creator.create("Magzhan");

        hero.equip(new MedievalEquipmentFactory());
        System.out.println("Hero created: " + hero);

        EnemyBuilder builder = new EnemyBuilder();
        Enemy orc = builder.title("Orc Grunt").vitality(48).power(9).armor(2).build();
        System.out.println("Enemy built: " + orc);

        EnemyCatalog catalog = new EnemyCatalog();
        catalog.register("BOSS_ORC", new EnemyBuilder().title("Orc Boss").vitality(60).power(12).armor(3).build());

        Enemy bossClone = catalog.createFromPrototype("BOSS_ORC");
        System.out.println("Enemy from prototype: " + bossClone);


        Combatant heroC = new HeroCombatantAdapter(hero);
        Combatant enemyC = new EnemyCombatantAdapter(bossClone);

        BattleEngine engine = BattleEngine.getInstance();
        engine.battle(heroC, enemyC);


        Hero mage = new MageCreator().create("Aruzhan");
        mage.equip(new MagicEquipmentFactory());
        Combatant mageC = new HeroCombatantAdapter(mage);

        Enemy archerEnemy = new EnemyBuilder().title("Bandit").vitality(42).power(10).armor(1).build();
        Combatant banditC = new EnemyCombatantAdapter(archerEnemy);

        engine.battle(mageC, banditC);
    }
}
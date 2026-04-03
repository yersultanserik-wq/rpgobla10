package com.narxoz.rpg;

import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.arena.ArenaOpponent;
import com.narxoz.rpg.arena.TournamentResult;
import com.narxoz.rpg.adapter.Combatant;
import com.narxoz.rpg.adapter.EnemyCombatantAdapter;
import com.narxoz.rpg.adapter.HeroCombatantAdapter;
import com.narxoz.rpg.battle.BattleEngine;
import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.EnemyBuilder;
import com.narxoz.rpg.enemy.EnemyCatalog;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.Hero;
import com.narxoz.rpg.hero.creator.ArcherCreator;
import com.narxoz.rpg.hero.creator.HeroCreator;
import com.narxoz.rpg.hero.creator.MageCreator;
import com.narxoz.rpg.hero.creator.WarriorCreator;
import com.narxoz.rpg.hero.equipment.MagicEquipmentFactory;
import com.narxoz.rpg.hero.equipment.MedievalEquipmentFactory;
import com.narxoz.rpg.hero.equipment.RangerEquipmentFactory;
import com.narxoz.rpg.tournament.TournamentEngine;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== HW1-HW3 DEMO ===");

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

        System.out.println("\n=== HW5 DECORATOR DEMO ===");
        Hero demoArcher = new ArcherCreator().create("Ayan");
        demoArcher.equip(new RangerEquipmentFactory());
        Combatant demoHero = new HeroCombatantAdapter(demoArcher);

        Enemy dummy1 = new EnemyBuilder().title("Training Dummy Alpha").vitality(40).power(0).armor(0).build();
        Enemy dummy2 = new EnemyBuilder().title("Training Dummy Beta").vitality(40).power(0).armor(0).build();
        Enemy dummy3 = new EnemyBuilder().title("Training Dummy Gamma").vitality(40).power(0).armor(0).build();

        AttackAction baseAttack = new BasicAttack("Precision Shot", 2);
        AttackAction fireCritical = new CriticalFocusDecorator(new FireRuneDecorator(baseAttack));
        AttackAction poisonFireCritical = new PoisonCoatingDecorator(new FireRuneDecorator(new CriticalFocusDecorator(baseAttack)));

        System.out.println("\nDecorator demo 1: base attack");
        baseAttack.execute(demoHero, new EnemyCombatantAdapter(dummy1));

        System.out.println("\nDecorator demo 2: fire + critical");
        fireCritical.execute(demoHero, new EnemyCombatantAdapter(dummy2));

        System.out.println("\nDecorator demo 3: critical + fire + poison (different order)");
        poisonFireCritical.execute(demoHero, new EnemyCombatantAdapter(dummy3));

        System.out.println("\n=== HW5 FACADE DEMO ===");
        Hero dungeonHero = new WarriorCreator().create("Taukekhan");
        dungeonHero.equip(new MedievalEquipmentFactory());
        Combatant dungeonHeroC = new HeroCombatantAdapter(dungeonHero);

        Enemy dungeonBoss = new EnemyBuilder()
                .title("Ancient Lich")
                .vitality(55)
                .power(11)
                .armor(2)
                .build();
        Combatant bossC = new EnemyCombatantAdapter(dungeonBoss);

        AttackAction dungeonAction = new CriticalFocusDecorator(
                new PoisonCoatingDecorator(
                        new FireRuneDecorator(
                                new BasicAttack("Dungeon Cleaver", 3)
                        )
                )
        );

        DungeonFacade facade = new DungeonFacade();
        AdventureResult result = facade.runAdventure("Shadow Crypt", dungeonHeroC, bossC, dungeonAction);

        System.out.println("\n=== ADVENTURE SUMMARY ===");
        System.out.println(result.summary());

        System.out.println("\n=== HW6 COMMAND + CHAIN DEMO ===");
        Hero arenaHeroBase = new ArcherCreator().create("Sanzhar");
        arenaHeroBase.equip(new RangerEquipmentFactory());
        ArenaFighter arenaHero = new ArenaFighter(arenaHeroBase);

        Enemy arenaEnemyBase = new EnemyBuilder()
                .title("Arena Minotaur")
                .vitality(78)
                .power(14)
                .armor(2)
                .build();
        ArenaOpponent arenaOpponent = new ArenaOpponent(arenaEnemyBase);

        TournamentEngine tournamentEngine = new TournamentEngine();
        TournamentResult tournamentResult = tournamentEngine.runBattle(arenaHero, arenaOpponent);
    }
}

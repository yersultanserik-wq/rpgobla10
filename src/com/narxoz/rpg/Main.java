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
import com.narxoz.rpg.combatant.DungeonBoss;
import com.narxoz.rpg.engine.DungeonEngine;
import com.narxoz.rpg.engine.EncounterResult;
import com.narxoz.rpg.floor.MonsterFloor;
import com.narxoz.rpg.floor.RestFloor;
import com.narxoz.rpg.floor.TowerFloor;
import com.narxoz.rpg.floor.TrapFloor;
import com.narxoz.rpg.observer.AchievementObserver;
import com.narxoz.rpg.observer.BattleLogger;
import com.narxoz.rpg.observer.GameEventPublisher;
import com.narxoz.rpg.observer.PartySupportObserver;
import com.narxoz.rpg.tower.TowerRunResult;
import com.narxoz.rpg.tower.TowerRunner;

import java.util.Arrays;
import java.util.List;

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
        System.out.println("\n=== HW7 STRATEGY + OBSERVER DEMO ===");
        GameEventPublisher publisher = new GameEventPublisher();
        publisher.addObserver(new BattleLogger());
        publisher.addObserver(new AchievementObserver());

        com.narxoz.rpg.combatant.Hero knight = new com.narxoz.rpg.combatant.Hero("Magzhan", 90, 18, 4);
        com.narxoz.rpg.combatant.Hero ranger = new com.narxoz.rpg.combatant.Hero("Aruzhan", 72, 20, 3);
        publisher.addObserver(new PartySupportObserver(knight));
        publisher.addObserver(new PartySupportObserver(ranger));

        DungeonBoss cursedBoss = new DungeonBoss("Cursed Dungeon Lord", 120, 14, 3, publisher);
        DungeonEngine dungeonEngine = new DungeonEngine(publisher);
        EncounterResult encounter = dungeonEngine.runEncounter(Arrays.asList(knight, ranger), cursedBoss);
        System.out.println("HW7 result: " + encounter.summary() + " Rounds=" + encounter.rounds());

        System.out.println("\n=== HW8 STATE + TEMPLATE METHOD DEMO ===");
        com.narxoz.rpg.combatant.Hero towerWarrior = new com.narxoz.rpg.combatant.Hero("Taukekhan", 85, 17, 5);
        com.narxoz.rpg.combatant.Hero towerMage = new com.narxoz.rpg.combatant.Hero("Sanzhar", 65, 22, 2);
        List<com.narxoz.rpg.combatant.Hero> towerParty = Arrays.asList(towerWarrior, towerMage);
        List<TowerFloor> floors = Arrays.asList(new TrapFloor(1), new MonsterFloor(2), new RestFloor(3), new MonsterFloor(4));
        TowerRunner towerRunner = new TowerRunner();
        TowerRunResult towerResult = towerRunner.run(towerParty, floors);
        System.out.println("HW8 result: " + towerResult.summary() + " Floors cleared=" + towerResult.floorsCleared());

    }
}

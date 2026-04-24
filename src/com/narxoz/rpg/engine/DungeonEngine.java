package com.narxoz.rpg.engine;

import com.narxoz.rpg.combatant.DungeonBoss;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.observer.GameEvent;
import com.narxoz.rpg.observer.GameEventPublisher;
import com.narxoz.rpg.observer.GameEventType;

import java.util.List;

public class DungeonEngine {
    private final GameEventPublisher publisher;

    public DungeonEngine(GameEventPublisher publisher) {
        this.publisher = publisher;
    }

    public EncounterResult runEncounter(List<Hero> heroes, DungeonBoss boss) {
        int rounds = 0;
        while (boss.isAlive() && anyHeroAlive(heroes) && rounds < 20) {
            rounds++;
            System.out.println("\n-- Dungeon round " + rounds + " --");

            for (Hero hero : heroes) {
                if (!hero.isAlive() || !boss.isAlive()) continue;
                int damage = hero.attack();
                boss.takeDamage(damage);
                publisher.notifyObservers(new GameEvent(GameEventType.ATTACK_LANDED, hero.getName(), boss.getName(), damage, "Hero attacked boss."));
                System.out.println(hero.getName() + " hits " + boss.getName() + " for " + damage + ". " + boss);
            }

            if (!boss.isAlive()) {
                publisher.notifyObservers(new GameEvent(GameEventType.BOSS_DEFEATED, boss.getName(), "party", rounds, "Boss defeated by party."));
                break;
            }

            Hero target = firstAlive(heroes);
            if (target != null) {
                int damage = boss.attack();
                target.takeDamage(damage);
                publisher.notifyObservers(new GameEvent(GameEventType.ATTACK_LANDED, boss.getName(), target.getName(), damage, "Boss attacked hero."));
                System.out.println(boss.getName() + " uses " + boss.getStrategyName() + " and hits " + target.getName() + " for " + damage + ". " + target);
                if (target.isAlive() && target.hpPercent() <= 30) {
                    publisher.notifyObservers(new GameEvent(GameEventType.HERO_LOW_HP, boss.getName(), target.getName(), target.getHp(), "Hero dropped below 30% HP."));
                }
                if (!target.isAlive()) {
                    publisher.notifyObservers(new GameEvent(GameEventType.HERO_DEFEATED, boss.getName(), target.getName(), 0, "Hero has fallen."));
                }
            }
        }

        boolean win = boss.getHp() <= 0;
        return new EncounterResult(win, rounds, win ? "Party defeated the cursed dungeon boss." : "Party failed to defeat the boss.");
    }

    private boolean anyHeroAlive(List<Hero> heroes) {
        for (Hero hero : heroes) if (hero.isAlive()) return true;
        return false;
    }

    private Hero firstAlive(List<Hero> heroes) {
        for (Hero hero : heroes) if (hero.isAlive()) return hero;
        return null;
    }
}

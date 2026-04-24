package com.narxoz.rpg.combatant;

import com.narxoz.rpg.observer.GameEvent;
import com.narxoz.rpg.observer.GameEventPublisher;
import com.narxoz.rpg.observer.GameEventType;
import com.narxoz.rpg.strategy.AggressiveStrategy;
import com.narxoz.rpg.strategy.CombatStrategy;
import com.narxoz.rpg.strategy.DefensiveStrategy;
import com.narxoz.rpg.strategy.EnragedStrategy;

public class DungeonBoss {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int baseAttack;
    private final int baseDefense;
    private CombatStrategy strategy;
    private final GameEventPublisher publisher;
    private int phase = 1;

    public DungeonBoss(String name, int maxHp, int baseAttack, int baseDefense, GameEventPublisher publisher) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.publisher = publisher;
        this.strategy = new AggressiveStrategy();
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public boolean isAlive() { return hp > 0; }
    public String getStrategyName() { return strategy.name(); }

    public int attack() { return Math.max(1, strategy.calculateDamage(baseAttack)); }

    public void takeDamage(int rawDamage) {
        int damage = Math.max(0, rawDamage - strategy.calculateDefense(baseDefense));
        hp = Math.max(0, hp - damage);
        checkPhaseTransition();
    }

    private void checkPhaseTransition() {
        int percent = (int) Math.round((hp * 100.0) / maxHp);
        if (phase == 1 && percent <= 66) {
            phase = 2;
            strategy = new DefensiveStrategy();
            publisher.notifyObservers(new GameEvent(GameEventType.BOSS_PHASE_CHANGED, name, name, phase, "Boss switched to Defensive strategy at <= 66% HP."));
        } else if (phase == 2 && percent <= 33) {
            phase = 3;
            strategy = new EnragedStrategy();
            publisher.notifyObservers(new GameEvent(GameEventType.BOSS_PHASE_CHANGED, name, name, phase, "Boss switched to Enraged strategy at <= 33% HP."));
        }
    }

    @Override public String toString() {
        return name + " [HP=" + hp + "/" + maxHp + ", Phase=" + phase + ", Strategy=" + strategy.name() + "]";
    }
}

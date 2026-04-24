package com.narxoz.rpg.combatant;

import com.narxoz.rpg.state.HeroState;
import com.narxoz.rpg.state.NormalState;
import com.narxoz.rpg.strategy.BalancedStrategy;
import com.narxoz.rpg.strategy.CombatStrategy;

public class Hero {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int baseAttack;
    private final int baseDefense;
    private CombatStrategy strategy;
    private HeroState state;

    public Hero(String name, int maxHp, int baseAttack, int baseDefense) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.strategy = new BalancedStrategy();
        this.state = new NormalState();
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public boolean isAlive() { return hp > 0; }
    public int hpPercent() { return (int) Math.round((hp * 100.0) / maxHp); }

    public void setStrategy(CombatStrategy strategy) { this.strategy = strategy; }
    public String getStrategyName() { return strategy.name(); }

    public void setState(HeroState state) { this.state = state; }
    public HeroState getState() { return state; }

    public int attack() {
        int damage = strategy.calculateDamage(baseAttack);
        return Math.max(1, state.modifyDamageDealt(this, damage));
    }

    public void takeDamage(int rawDamage) {
        int defended = Math.max(0, rawDamage - strategy.calculateDefense(baseDefense));
        int finalDamage = Math.max(0, state.modifyDamageReceived(this, defended));
        hp = Math.max(0, hp - finalDamage);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + Math.max(0, amount));
    }

    public void tickState() {
        state = state.nextState(this);
    }

    @Override public String toString() {
        return name + " [HP=" + hp + "/" + maxHp + ", Strategy=" + strategy.name() + ", State=" + state.name() + "]";
    }
}

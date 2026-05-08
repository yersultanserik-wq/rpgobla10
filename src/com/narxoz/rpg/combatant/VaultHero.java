package com.narxoz.rpg.combatant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A vault-exploring hero whose mutable state (HP, mana, gold, inventory)
 * can be snapshot-ed into a {@link HeroMemento} and later restored.
 *
 * This class lives in the same package as HeroMemento so it can access
 * the package-private memento constructor and accessors directly.
 */
public class VaultHero {
    private final String name;
    private final int    maxHp;
    private final int    maxMana;

    private int          hp;
    private int          mana;
    private int          gold;
    private List<String> inventory; // names of collected items

    public VaultHero(String name, int maxHp, int maxMana, int startGold) {
        this.name      = name;
        this.maxHp     = maxHp;
        this.maxMana   = maxMana;
        this.hp        = maxHp;
        this.mana      = maxMana;
        this.gold      = startGold;
        this.inventory = new ArrayList<>();
    }

    // ------------------------------------------------------------------ //
    //  Memento hooks
    // ------------------------------------------------------------------ //

    /** Originator — creates and returns a memento capturing current state. */
    public HeroMemento saveMemento(String label) {
        return new HeroMemento(hp, mana, gold, inventory, label);
    }

    /** Originator — restores state from a memento (package-private accessors). */
    public void restoreMemento(HeroMemento m) {
        this.hp        = m.getHp();
        this.mana      = m.getMana();
        this.gold      = m.getGold();
        this.inventory = new ArrayList<>(m.getInventory());
        System.out.println("  [Rewind] " + name + " restored to checkpoint \""
                           + m.getLabel() + "\" → " + statusLine());
    }

    // ------------------------------------------------------------------ //
    //  State-mutating actions (simulate vault exploration)
    // ------------------------------------------------------------------ //

    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    public void spendMana(int amount) {
        mana = Math.max(0, mana - amount);
    }

    public void recoverMana(int amount) {
        mana = Math.min(maxMana, mana + amount);
    }

    public void earnGold(int amount) {
        gold += amount;
    }

    public void spendGold(int amount) {
        gold = Math.max(0, gold - amount);
    }

    public void pickUp(String itemName) {
        inventory.add(itemName);
    }

    public void drop(String itemName) {
        inventory.remove(itemName);
    }

    // ------------------------------------------------------------------ //
    //  Accessors
    // ------------------------------------------------------------------ //

    public String getName()          { return name; }
    public int    getHp()            { return hp; }
    public int    getMaxHp()         { return maxHp; }
    public int    getMana()          { return mana; }
    public int    getMaxMana()       { return maxMana; }
    public int    getGold()          { return gold; }
    public boolean isAlive()         { return hp > 0; }
    public List<String> getInventory() { return Collections.unmodifiableList(inventory); }

    public String statusLine() {
        return name + " [HP=" + hp + "/" + maxHp
               + ", Mana=" + mana + "/" + maxMana
               + ", Gold=" + gold
               + ", Inv=" + inventory + "]";
    }

    @Override
    public String toString() { return statusLine(); }
}

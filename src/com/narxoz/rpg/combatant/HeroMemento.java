package com.narxoz.rpg.combatant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Memento — an immutable snapshot of a Hero's mutable state.
 *
 * Accessors are package-private so only Hero (same package) and
 * the Caretaker (which receives HeroMemento but only stores/returns it)
 * interact with the internals. External classes see only an opaque token.
 */
public final class HeroMemento {
    private final int    hp;
    private final int    mana;
    private final int    gold;
    private final List<String> inventorySnapshot;
    private final String label; // human-readable timestamp / checkpoint name

    /** Called only by Hero#saveMemento(). */
    HeroMemento(int hp, int mana, int gold, List<String> inventory, String label) {
        this.hp                = hp;
        this.mana              = mana;
        this.gold              = gold;
        this.inventorySnapshot = Collections.unmodifiableList(new ArrayList<>(inventory));
        this.label             = label;
    }

    // ---------- package-private accessors — only Hero reads these ----------
    int          getHp()        { return hp; }
    int          getMana()      { return mana; }
    int          getGold()      { return gold; }
    List<String> getInventory() { return inventorySnapshot; }

    // ---------- public accessor — safe to expose to anyone ----------------
    public String getLabel() { return label; }

    @Override
    public String toString() {
        return "HeroMemento[" + label + " | HP=" + hp + ", mana=" + mana
               + ", gold=" + gold + ", inv=" + inventorySnapshot + "]";
    }
}

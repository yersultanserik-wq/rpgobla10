package com.narxoz.rpg.artifact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Visitor #2 — Curse Detection Report.
 * Scans every artifact for signs of cursed enchantment.
 * No artifact class had to change to add this new report.
 */
public class CurseDetectionVisitor implements ArtifactVisitor {
    private final List<String> cursedItems = new ArrayList<>();

    @Override
    public void visit(Weapon weapon) {
        // "shadow" or "void" damage types hint at cursed origin
        boolean cursed = weapon.getDamageType().contains("shadow")
                      || weapon.getDamageType().contains("void");
        flag(weapon.getName(), "Weapon", cursed, "damage type: " + weapon.getDamageType());
    }

    @Override
    public void visit(Potion potion) {
        // Potions that claim to cure curses might themselves be cursed bait
        boolean suspicious = potion.curesCurse() && potion.getHealAmount() < 10;
        flag(potion.getName(), "Potion", suspicious, "low-heal curse-cure — suspicious");
    }

    @Override
    public void visit(Scroll scroll) {
        // Scrolls with zero mana cost are suspicious — free power often has a price
        boolean cursed = scroll.getManaCost() == 0;
        flag(scroll.getName(), "Scroll", cursed, "zero mana cost scroll");
    }

    @Override
    public void visit(Ring ring) {
        // "time-warp" and "void" enchantments are known Chronomancer curses
        boolean cursed = ring.getEnchantment().contains("time-warp")
                      || ring.getEnchantment().contains("void");
        flag(ring.getName(), "Ring", cursed, "enchantment: " + ring.getEnchantment());
    }

    @Override
    public void visit(Armor armor) {
        // Crystal armor sometimes traps the wearer's soul
        boolean cursed = armor.getMaterial().contains("soul-crystal");
        flag(armor.getName(), "Armor", cursed, "material: " + armor.getMaterial());
    }

    private void flag(String name, String type, boolean cursed, String detail) {
        if (cursed) {
            cursedItems.add(name);
            System.out.println("  [Curse] ⚠  " + type + " \"" + name + "\" — CURSED! (" + detail + ")");
        } else {
            System.out.println("  [Curse] ✓  " + type + " \"" + name + "\" — clean");
        }
    }

    public List<String> getCursedItemNames() {
        return Collections.unmodifiableList(cursedItems);
    }

    public String summary() {
        if (cursedItems.isEmpty()) return "Curse Detection: all items clean.";
        return "Curse Detection: " + cursedItems.size() + " cursed item(s): " + cursedItems;
    }
}

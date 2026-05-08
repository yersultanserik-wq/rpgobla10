package com.narxoz.rpg.artifact;

/**
 * Visitor #3 — Encumbrance Report.
 * Calculates the total carry weight of an inventory.
 * Armor and weapons are heavy; potions and scrolls are light.
 */
public class EncumbranceVisitor implements ArtifactVisitor {
    private double totalWeight = 0.0;
    private int    itemCount   = 0;

    // Weight constants (in kilograms)
    private static final double WEAPON_BASE_KG = 3.0;
    private static final double POTION_BASE_KG = 0.5;
    private static final double SCROLL_BASE_KG = 0.1;
    private static final double RING_BASE_KG   = 0.05;
    private static final double ARMOR_BASE_KG  = 8.0;

    @Override
    public void visit(Weapon weapon) {
        double w = WEAPON_BASE_KG + (weapon.getAttackBonus() * 0.3);
        totalWeight += w;
        itemCount++;
        System.out.printf("  [Weight] Weapon  \"%s\" → %.1f kg%n", weapon.getName(), w);
    }

    @Override
    public void visit(Potion potion) {
        totalWeight += POTION_BASE_KG;
        itemCount++;
        System.out.printf("  [Weight] Potion  \"%s\" → %.1f kg%n", potion.getName(), POTION_BASE_KG);
    }

    @Override
    public void visit(Scroll scroll) {
        totalWeight += SCROLL_BASE_KG;
        itemCount++;
        System.out.printf("  [Weight] Scroll  \"%s\" → %.1f kg%n", scroll.getName(), SCROLL_BASE_KG);
    }

    @Override
    public void visit(Ring ring) {
        totalWeight += RING_BASE_KG;
        itemCount++;
        System.out.printf("  [Weight] Ring    \"%s\" → %.2f kg%n", ring.getName(), RING_BASE_KG);
    }

    @Override
    public void visit(Armor armor) {
        double w = ARMOR_BASE_KG + (armor.getDefenseBonus() * 0.5);
        totalWeight += w;
        itemCount++;
        System.out.printf("  [Weight] Armor   \"%s\" → %.1f kg%n", armor.getName(), w);
    }

    public double getTotalWeight() { return totalWeight; }

    public String summary() {
        return String.format("Encumbrance: %d items, total weight %.2f kg", itemCount, totalWeight);
    }
}

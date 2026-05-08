package com.narxoz.rpg.artifact;

/**
 * Visitor #1 — Gold Appraisal Report.
 * Tallies the total gold value of every artifact in the inventory.
 * Weapons get a combat-rarity multiplier; Rings are luxury items.
 */
public class GoldAppraisalVisitor implements ArtifactVisitor {
    private int totalGold = 0;
    private int itemCount = 0;

    @Override
    public void visit(Weapon weapon) {
        // Combat weapons are valued higher based on attack bonus
        int value = weapon.getBaseValue() + (weapon.getAttackBonus() * 15);
        totalGold += value;
        itemCount++;
        System.out.println("  [Appraise] Weapon  \"" + weapon.getName() + "\" → " + value + "g");
    }

    @Override
    public void visit(Potion potion) {
        int value = potion.getBaseValue() + (potion.curesCurse() ? 50 : 0);
        totalGold += value;
        itemCount++;
        System.out.println("  [Appraise] Potion  \"" + potion.getName() + "\" → " + value + "g");
    }

    @Override
    public void visit(Scroll scroll) {
        // Rare spells command higher prices
        int value = scroll.getBaseValue() + (scroll.getManaCost() * 5);
        totalGold += value;
        itemCount++;
        System.out.println("  [Appraise] Scroll  \"" + scroll.getName() + "\" → " + value + "g");
    }

    @Override
    public void visit(Ring ring) {
        // Rings are luxury — mana bonus drives up price
        int value = ring.getBaseValue() + (ring.getManaBonus() * 20);
        totalGold += value;
        itemCount++;
        System.out.println("  [Appraise] Ring    \"" + ring.getName() + "\" → " + value + "g");
    }

    @Override
    public void visit(Armor armor) {
        int value = armor.getBaseValue() + (armor.getDefenseBonus() * 10);
        totalGold += value;
        itemCount++;
        System.out.println("  [Appraise] Armor   \"" + armor.getName() + "\" → " + value + "g");
    }

    public int getTotalGold()  { return totalGold; }
    public int getItemCount()  { return itemCount; }

    public String summary() {
        return "Gold Appraisal: " + itemCount + " items → total " + totalGold + "g";
    }
}

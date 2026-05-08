package com.narxoz.rpg.artifact;

/**
 * An enchanted ring artifact found in the Chronomancer's Vault.
 */
public class Ring extends Artifact {
    private final int manaBonus;
    private final String enchantment; // e.g. "time-warp", "shadow", "luck"

    public Ring(String name, int baseValue, int manaBonus, String enchantment) {
        super(name, baseValue);
        this.manaBonus   = manaBonus;
        this.enchantment = enchantment;
    }

    public int getManaBonus()      { return manaBonus; }
    public String getEnchantment() { return enchantment; }

    @Override
    public void accept(ArtifactVisitor visitor) {
        visitor.visit(this);
    }
}

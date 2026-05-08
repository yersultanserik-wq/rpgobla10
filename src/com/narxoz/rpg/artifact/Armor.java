package com.narxoz.rpg.artifact;

/**
 * An armor artifact found in the Chronomancer's Vault.
 */
public class Armor extends Artifact {
    private final int defenseBonus;
    private final String material; // e.g. "steel", "dragonhide", "crystal"

    public Armor(String name, int baseValue, int defenseBonus, String material) {
        super(name, baseValue);
        this.defenseBonus = defenseBonus;
        this.material     = material;
    }

    public int getDefenseBonus() { return defenseBonus; }
    public String getMaterial()  { return material; }

    @Override
    public void accept(ArtifactVisitor visitor) {
        visitor.visit(this);
    }
}

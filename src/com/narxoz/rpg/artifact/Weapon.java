package com.narxoz.rpg.artifact;

/**
 * A weapon artifact found in the Chronomancer's Vault.
 */
public class Weapon extends Artifact {
    private final int attackBonus;
    private final String damageType; // e.g. "slashing", "fire", "arcane"

    public Weapon(String name, int baseValue, int attackBonus, String damageType) {
        super(name, baseValue);
        this.attackBonus = attackBonus;
        this.damageType  = damageType;
    }

    public int getAttackBonus() { return attackBonus; }
    public String getDamageType() { return damageType; }

    @Override
    public void accept(ArtifactVisitor visitor) {
        visitor.visit(this); // double-dispatch
    }
}

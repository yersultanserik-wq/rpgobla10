package com.narxoz.rpg.artifact;

/**
 * A consumable potion artifact found in the Chronomancer's Vault.
 */
public class Potion extends Artifact {
    private final int healAmount;
    private final boolean curesCurse;

    public Potion(String name, int baseValue, int healAmount, boolean curesCurse) {
        super(name, baseValue);
        this.healAmount  = healAmount;
        this.curesCurse  = curesCurse;
    }

    public int getHealAmount()  { return healAmount; }
    public boolean curesCurse() { return curesCurse; }

    @Override
    public void accept(ArtifactVisitor visitor) {
        visitor.visit(this);
    }
}

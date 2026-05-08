package com.narxoz.rpg.artifact;

/**
 * A magic scroll artifact found in the Chronomancer's Vault.
 */
public class Scroll extends Artifact {
    private final String spellName;
    private final int manaCost;

    public Scroll(String name, int baseValue, String spellName, int manaCost) {
        super(name, baseValue);
        this.spellName = spellName;
        this.manaCost  = manaCost;
    }

    public String getSpellName() { return spellName; }
    public int getManaCost()     { return manaCost; }

    @Override
    public void accept(ArtifactVisitor visitor) {
        visitor.visit(this);
    }
}

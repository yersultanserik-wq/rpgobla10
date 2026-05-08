package com.narxoz.rpg.artifact;

/**
 * Abstract base for every vault artifact.
 * Each subclass implements accept() with a single self-dispatch call,
 * enabling true double-dispatch without instanceof chains.
 */
public abstract class Artifact {
    private final String name;
    private final int baseValue; // gold value

    protected Artifact(String name, int baseValue) {
        this.name = name;
        this.baseValue = baseValue;
    }

    public String getName()     { return name; }
    public int getBaseValue()   { return baseValue; }

    /** Double-dispatch hook — concrete subclass calls visitor.visit(this). */
    public abstract void accept(ArtifactVisitor visitor);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + name + ", val=" + baseValue + "g]";
    }
}

package com.narxoz.rpg.artifact;

/**
 * Visitor interface — one overload per concrete artifact type.
 * Adding a new report means creating a new ArtifactVisitor implementation,
 * NOT touching any artifact class (Open/Closed Principle).
 */
public interface ArtifactVisitor {
    void visit(Weapon weapon);
    void visit(Potion potion);
    void visit(Scroll scroll);
    void visit(Ring ring);
    void visit(Armor armor);
}

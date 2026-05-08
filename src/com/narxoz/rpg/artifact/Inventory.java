package com.narxoz.rpg.artifact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds a mixed collection of Artifacts and drives visitor traversal.
 * The inventory itself has no knowledge of what each visitor does —
 * it simply iterates and calls accept() on each artifact.
 */
public class Inventory {
    private final List<Artifact> items = new ArrayList<>();

    public void add(Artifact artifact) {
        items.add(artifact);
    }

    public void remove(Artifact artifact) {
        items.remove(artifact);
    }

    /** Apply a visitor to every artifact in the inventory. */
    public void accept(ArtifactVisitor visitor) {
        for (Artifact artifact : items) {
            artifact.accept(visitor);
        }
    }

    public List<Artifact> all() {
        return Collections.unmodifiableList(items);
    }

    public int size() { return items.size(); }
}

package com.narxoz.rpg.memento;

import com.narxoz.rpg.combatant.HeroMemento;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

/**
 * Caretaker — manages the Memento stack for the Chronomancer's Vault.
 *
 * Responsibilities:
 *   • Store mementos pushed by the VaultHero (Originator).
 *   • Return them on demand for restoration.
 *   • Never inspect memento internals (the fields are package-private).
 *
 * Uses a Deque as a stack so the most recent checkpoint is always on top.
 */
public class Caretaker {
    private final Deque<HeroMemento> history = new ArrayDeque<>();
    private final int maxDepth; // how many checkpoints to retain

    public Caretaker(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public Caretaker() {
        this(10);
    }

    /**
     * Save a checkpoint.
     * If the history exceeds maxDepth, the oldest entry is discarded.
     */
    public void push(HeroMemento memento) {
        history.push(memento);
        if (history.size() > maxDepth) {
            // Remove the oldest (bottom of deque)
            ((ArrayDeque<HeroMemento>) history).removeLast();
        }
        System.out.println("  [Caretaker] Saved checkpoint: \"" + memento.getLabel()
                           + "\" (stack depth=" + history.size() + ")");
    }

    /**
     * Pop and return the most recent checkpoint, or empty if none.
     */
    public Optional<HeroMemento> pop() {
        if (history.isEmpty()) return Optional.empty();
        HeroMemento m = history.pop();
        System.out.println("  [Caretaker] Popped checkpoint: \"" + m.getLabel() + "\"");
        return Optional.of(m);
    }

    /**
     * Peek at the most recent checkpoint without removing it.
     */
    public Optional<HeroMemento> peek() {
        return history.isEmpty() ? Optional.empty() : Optional.of(history.peek());
    }

    public int depth() { return history.size(); }

    public boolean isEmpty() { return history.isEmpty(); }

    /** Print the entire checkpoint stack (newest → oldest). */
    public void printHistory() {
        System.out.println("  [Caretaker] Checkpoint stack (newest first):");
        if (history.isEmpty()) {
            System.out.println("    (empty)");
        } else {
            int i = 1;
            for (HeroMemento m : history) {
                System.out.println("    " + i++ + ". \"" + m.getLabel() + "\"");
            }
        }
    }
}

package com.narxoz.rpg.quest;

/**
 * Custom iterator interface for the QuestLog.
 * Mirrors java.util.Iterator but is domain-specific so we can add
 * quest-specific helpers (e.g. peek) without polluting the standard API.
 */
public interface QuestIterator {
    /** Returns true if there are more quests to traverse. */
    boolean hasNext();

    /** Returns the next quest and advances the cursor. */
    Quest next();

    /** Resets the cursor to the beginning without creating a new iterator. */
    void reset();
}

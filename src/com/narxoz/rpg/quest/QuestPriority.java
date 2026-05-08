package com.narxoz.rpg.quest;

/**
 * Priority levels for guild quests, ordered from lowest to highest urgency.
 */
public enum QuestPriority {
    LOW,
    NORMAL,
    HIGH,
    CRITICAL;

    /** Returns true if this priority is at least as urgent as the given threshold. */
    public boolean isAtLeast(QuestPriority threshold) {
        return this.ordinal() >= threshold.ordinal();
    }
}

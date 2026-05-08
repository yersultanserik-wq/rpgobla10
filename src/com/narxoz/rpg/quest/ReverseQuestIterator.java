package com.narxoz.rpg.quest;

/**
 * Iterator #2 — Reverse-arrival traversal (newest quest first).
 * Walks the QuestLog from size-1 down to 0.
 */
class ReverseQuestIterator implements QuestIterator {
    private final QuestLog log;
    private int cursor;

    ReverseQuestIterator(QuestLog log) {
        this.log    = log;
        this.cursor = log.quests.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return cursor >= 0;
    }

    @Override
    public Quest next() {
        if (!hasNext()) throw new java.util.NoSuchElementException("No more quests");
        return log.quests.get(cursor--);
    }

    @Override
    public void reset() {
        cursor = log.quests.size() - 1;
    }
}

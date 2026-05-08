package com.narxoz.rpg.quest;

/**
 * Iterator #1 — Arrival-order traversal.
 * Walks the QuestLog from index 0 to size-1.
 */
class OrderedQuestIterator implements QuestIterator {
    private final QuestLog log;
    private int cursor;

    OrderedQuestIterator(QuestLog log) {
        this.log    = log;
        this.cursor = 0;
    }

    @Override
    public boolean hasNext() {
        return cursor < log.quests.size();
    }

    @Override
    public Quest next() {
        if (!hasNext()) throw new java.util.NoSuchElementException("No more quests");
        return log.quests.get(cursor++);
    }

    @Override
    public void reset() {
        cursor = 0;
    }
}

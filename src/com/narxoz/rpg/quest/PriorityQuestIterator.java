package com.narxoz.rpg.quest;

/**
 * Iterator #3 — Priority-filtered traversal.
 * Skips any quest whose priority is below the given threshold.
 * Maintains arrival order among the accepted quests.
 */
class PriorityQuestIterator implements QuestIterator {
    private final QuestLog      log;
    private final QuestPriority minPriority;
    private int cursor;

    PriorityQuestIterator(QuestLog log, QuestPriority minPriority) {
        this.log         = log;
        this.minPriority = minPriority;
        this.cursor      = 0;
        advancePastLowPriority();
    }

    @Override
    public boolean hasNext() {
        return cursor < log.quests.size();
    }

    @Override
    public Quest next() {
        if (!hasNext()) throw new java.util.NoSuchElementException("No more quests at priority >= " + minPriority);
        Quest q = log.quests.get(cursor++);
        advancePastLowPriority();
        return q;
    }

    @Override
    public void reset() {
        cursor = 0;
        advancePastLowPriority();
    }

    /** Moves cursor forward until it points at a qualifying quest (or past the end). */
    private void advancePastLowPriority() {
        while (cursor < log.quests.size()
               && !log.quests.get(cursor).getPriority().isAtLeast(minPriority)) {
            cursor++;
        }
    }
}

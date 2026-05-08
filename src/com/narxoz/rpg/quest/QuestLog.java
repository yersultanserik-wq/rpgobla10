package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.List;

/**
 * QuestLog — the Aggregate in the Iterator pattern.
 *
 * Hides its internal {@code List<Quest>} completely.
 * Clients traverse quests only through iterators returned by the factory
 * methods below — they never get a reference to the backing list.
 */
public class QuestLog {
    /** Backing store — package-private so iterators in the same package can read it. */
    final List<Quest> quests = new ArrayList<>();

    // ------------------------------------------------------------------ //
    //  Aggregate operations
    // ------------------------------------------------------------------ //

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public int size() { return quests.size(); }

    public boolean isEmpty() { return quests.isEmpty(); }

    // ------------------------------------------------------------------ //
    //  Iterator factory methods — the only way outsiders traverse quests
    // ------------------------------------------------------------------ //

    /** Traverses quests in the order they were added (arrival order). */
    public QuestIterator orderedIterator() {
        return new OrderedQuestIterator(this);
    }

    /** Traverses quests from newest to oldest (reverse arrival order). */
    public QuestIterator reverseIterator() {
        return new ReverseQuestIterator(this);
    }

    /**
     * Traverses only quests whose priority is at least {@code minPriority},
     * in arrival order.
     */
    public QuestIterator priorityIterator(QuestPriority minPriority) {
        return new PriorityQuestIterator(this, minPriority);
    }
}

package com.narxoz.rpg.quest;

/**
 * Immutable quest record posted to the Guild's quest board.
 */
public final class Quest {
    private final String        id;
    private final String        title;
    private final String        description;
    private final QuestPriority priority;
    private final int           rewardGold;
    private final String        requiredRole; // "any", "scout", "healer", "captain", etc.

    public Quest(String id,
                 String title,
                 String description,
                 QuestPriority priority,
                 int rewardGold,
                 String requiredRole) {
        this.id           = id;
        this.title        = title;
        this.description  = description;
        this.priority     = priority;
        this.rewardGold   = rewardGold;
        this.requiredRole = requiredRole;
    }

    public String        getId()           { return id; }
    public String        getTitle()        { return title; }
    public String        getDescription()  { return description; }
    public QuestPriority getPriority()     { return priority; }
    public int           getRewardGold()   { return rewardGold; }
    public String        getRequiredRole() { return requiredRole; }

    @Override
    public String toString() {
        return "[" + priority + "] \"" + title + "\" (+" + rewardGold + "g, role=" + requiredRole + ")";
    }
}

package com.narxoz.rpg.council;

import java.util.List;

/**
 * Immutable summary of a War Council session.
 */
public final class CouncilRunResult {
    private final String       guildName;
    private final int          questsReviewed;
    private final int          criticalQuestsFound;
    private final int          messagesRouted;
    private final int          membersPresent;
    private final List<String> selectedQuestTitles;

    public CouncilRunResult(String guildName,
                            int questsReviewed,
                            int criticalQuestsFound,
                            int messagesRouted,
                            int membersPresent,
                            List<String> selectedQuestTitles) {
        this.guildName           = guildName;
        this.questsReviewed      = questsReviewed;
        this.criticalQuestsFound = criticalQuestsFound;
        this.messagesRouted      = messagesRouted;
        this.membersPresent      = membersPresent;
        this.selectedQuestTitles = List.copyOf(selectedQuestTitles);
    }

    public String       guildName()           { return guildName; }
    public int          questsReviewed()       { return questsReviewed; }
    public int          criticalQuestsFound()  { return criticalQuestsFound; }
    public int          messagesRouted()       { return messagesRouted; }
    public int          membersPresent()       { return membersPresent; }
    public List<String> selectedQuestTitles()  { return selectedQuestTitles; }

    public String summary() {
        return "CouncilRun[guild=" + guildName
               + ", questsReviewed=" + questsReviewed
               + ", critical=" + criticalQuestsFound
               + ", messages=" + messagesRouted
               + ", members=" + membersPresent + "]";
    }
}

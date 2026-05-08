package com.narxoz.rpg.council;

import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;

import java.util.ArrayList;
import java.util.List;

/**
 * CouncilEngine — orchestrates the War Council session.
 *
 * Demonstrates both patterns in sequence and then together:
 *   • Iterator  — walks QuestLog in three different traversal orders.
 *   • Mediator  — guild officers coordinate through GuildHall without
 *                 ever referencing each other directly.
 *   • Together  — the Captain briefs each CRITICAL quest; guild officers
 *                 react through the mediator in real time.
 */
public class CouncilEngine {

    public CouncilRunResult run(String guildName) {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     ADVENTURERS' GUILD — WAR COUNCIL     ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("Guild: " + guildName);

        // ── Build the Quest Log ──────────────────────────────────────────
        QuestLog log = buildQuestLog();
        System.out.println("\nQuest board loaded — " + log.size() + " quests posted.\n");

        // ── Iterator Demo 1: Arrival Order ───────────────────────────────
        System.out.println("--- Iterator #1: Arrival Order (all quests) ---");
        QuestIterator ordered = log.orderedIterator();
        int totalReviewed = 0;
        while (ordered.hasNext()) {
            Quest q = ordered.next();
            System.out.println("  " + q);
            totalReviewed++;
        }

        // ── Iterator Demo 2: Reverse Order ───────────────────────────────
        System.out.println("\n--- Iterator #2: Reverse Order (newest first) ---");
        QuestIterator reverse = log.reverseIterator();
        while (reverse.hasNext()) {
            System.out.println("  " + reverse.next());
        }

        // ── Iterator Demo 3: Priority Filter (HIGH+) ─────────────────────
        System.out.println("\n--- Iterator #3: Priority Filter (HIGH and CRITICAL only) ---");
        QuestIterator priority = log.priorityIterator(QuestPriority.HIGH);
        int criticalCount = 0;
        List<String> selectedTitles = new ArrayList<>();
        while (priority.hasNext()) {
            Quest q = priority.next();
            System.out.println("  ★ " + q);
            selectedTitles.add(q.getTitle());
            if (q.getPriority() == QuestPriority.CRITICAL) criticalCount++;
        }

        // ── Set up the Mediator (GuildHall) ──────────────────────────────
        System.out.println("\n--- Mediator Setup: Guild Hall opens ---");
        GuildHall hall        = new GuildHall(guildName + " Guild Hall");
        Captain       captain = new Captain("Baurzhan", hall);
        Quartermaster qm      = new Quartermaster("Dina", hall, 80);
        Scout         scout   = new Scout("Yerasyl", hall);
        Healer        healer  = new Healer("Aizat", hall, 30);

        // ── Mediator Demo: Officers coordinate ───────────────────────────
        System.out.println("\n--- Mediator Demo: Pre-campaign coordination ---");

        // Captain opens the war council
        captain.issueOrder("All officers report status before we review the quest board.");

        System.out.println();
        // Quartermaster reports supplies
        qm.reportSupplies();

        System.out.println();
        // Scout shares intelligence
        scout.reportIntelligence("Northern Pass", "Goblin warband spotted — 40 strong.");

        System.out.println();
        // Scout warns of a critical threat (triggers healer pre-staging + captain counter-order)
        scout.warnThreat("Troll patrol on the eastern road — ambush likely!");

        System.out.println();
        // Healer reports readiness
        healer.reportReadiness();

        System.out.println();
        // Captain requests resupply
        qm.requestResupply(25);

        // ── Iterator + Mediator together: Captain briefs CRITICAL quests ──
        System.out.println("\n--- Combined Demo: Captain briefs each CRITICAL quest ---");
        QuestIterator critOnly = log.priorityIterator(QuestPriority.CRITICAL);
        int messagesRouted = 6; // already sent above
        while (critOnly.hasNext()) {
            Quest q = critOnly.next();
            System.out.println("\n  [Council reviewing CRITICAL quest]: " + q.getTitle());
            captain.briefCampaign(q.getTitle());
            messagesRouted++;
        }

        // ── Result ────────────────────────────────────────────────────────
        CouncilRunResult result = new CouncilRunResult(
            guildName,
            totalReviewed,
            criticalCount,
            messagesRouted,
            hall.getMemberCount(),
            selectedTitles
        );

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     WAR COUNCIL CONCLUDED                ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println(result.summary());
        return result;
    }

    // ------------------------------------------------------------------ //
    //  Helper — build a realistic quest log
    // ------------------------------------------------------------------ //
    private QuestLog buildQuestLog() {
        QuestLog log = new QuestLog();
        log.addQuest(new Quest("Q01", "Clear the Goblin Cave",
                "Goblins have taken over the old mine near Aldenvale.",
                QuestPriority.NORMAL, 150, "any"));
        log.addQuest(new Quest("Q02", "Escort the Merchant Caravan",
                "Safe passage through Bandit Road to the capital.",
                QuestPriority.HIGH, 300, "captain"));
        log.addQuest(new Quest("Q03", "Retrieve the Stolen Grimoire",
                "A powerful spellbook was taken from the Mage's Tower.",
                QuestPriority.HIGH, 400, "scout"));
        log.addQuest(new Quest("Q04", "Cure the Plague in Millhaven",
                "A mysterious sickness is spreading — needs a healer urgently.",
                QuestPriority.CRITICAL, 600, "healer"));
        log.addQuest(new Quest("Q05", "Patrol the Southern Border",
                "Weekly patrol — low threat expected.",
                QuestPriority.LOW, 80, "any"));
        log.addQuest(new Quest("Q06", "Defeat the Dragon of Ashpeak",
                "Ancient dragon has awakened. All hands required.",
                QuestPriority.CRITICAL, 2000, "any"));
        log.addQuest(new Quest("Q07", "Investigate the Haunted Mill",
                "Villagers report strange noises at night.",
                QuestPriority.LOW, 60, "scout"));
        log.addQuest(new Quest("Q08", "Defend the Capital Gates",
                "An enemy army approaches. Mobilize immediately.",
                QuestPriority.CRITICAL, 1500, "captain"));
        return log;
    }
}

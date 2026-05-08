package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.VaultHero;
import com.narxoz.rpg.memento.Caretaker;

import java.util.ArrayList;
import java.util.List;

/**
 * ChronomancerEngine — orchestrates a Vault run.
 *
 * Demonstrates both patterns working together:
 *   • Visitor  — appraises the artifact inventory found in the vault.
 *   • Memento  — saves checkpoints before risky rooms; rewinds on failure.
 *
 * The engine is intentionally kept simple: it runs through a scripted
 * sequence of "rooms" (treasure, trap, boss) to make both patterns
 * clearly observable in the console output.
 */
public class ChronomancerEngine {

    /**
     * Run the vault with the given hero.
     * Returns a summary of the entire run.
     */
    public VaultRunResult run(VaultHero hero) {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║    CHRONOMANCER'S VAULT — ENTER      ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("Hero: " + hero);

        Caretaker caretaker = new Caretaker(5);
        Inventory vaultInventory = new Inventory();

        int roomsEntered   = 0;
        int trapsTriggered = 0;
        int rewindsUsed    = 0;
        List<String> appraisalLines = new ArrayList<>();

        // ── Room 1: Entry Hall — save initial checkpoint ────────────────
        System.out.println("\n--- Room 1: Entry Hall (checkpoint) ---");
        caretaker.push(hero.saveMemento("Entry Hall"));
        roomsEntered++;

        // ── Room 2: Treasure Cache — hero picks up artifacts ────────────
        System.out.println("\n--- Room 2: Treasure Cache ---");
        roomsEntered++;
        Weapon ancientSword = new Weapon("Ancient Chrono-Blade", 200, 8, "arcane");
        Potion elixir       = new Potion("Elixir of Ages", 80, 40, false);
        Ring   timeRing     = new Ring("Ring of Time-Warp", 350, 15, "time-warp");
        Scroll blastScroll  = new Scroll("Scroll of Nova Burst", 120, "Nova Burst", 30);
        Armor  voidArmor    = new Armor("Void-Crystal Plate", 300, 12, "soul-crystal");

        vaultInventory.add(ancientSword);
        vaultInventory.add(elixir);
        vaultInventory.add(timeRing);
        vaultInventory.add(blastScroll);
        vaultInventory.add(voidArmor);

        hero.pickUp(ancientSword.getName());
        hero.pickUp(elixir.getName());
        hero.pickUp(timeRing.getName());
        hero.earnGold(150);
        System.out.println("  Hero collected 3 artifacts and 150g. State: " + hero.statusLine());

        // ── Visitor Demo 1: Gold Appraisal ───────────────────────────────
        System.out.println("\n  >> Running Gold Appraisal on vault inventory...");
        GoldAppraisalVisitor appraiser = new GoldAppraisalVisitor();
        vaultInventory.accept(appraiser);
        appraisalLines.add(appraiser.summary());
        System.out.println("  >> " + appraiser.summary());

        // ── Visitor Demo 2: Curse Detection ──────────────────────────────
        System.out.println("\n  >> Running Curse Detection on vault inventory...");
        CurseDetectionVisitor curseDetector = new CurseDetectionVisitor();
        vaultInventory.accept(curseDetector);
        appraisalLines.add(curseDetector.summary());
        System.out.println("  >> " + curseDetector.summary());

        // ── Visitor Demo 3: Encumbrance ───────────────────────────────────
        System.out.println("\n  >> Running Encumbrance Report on vault inventory...");
        EncumbranceVisitor weightChecker = new EncumbranceVisitor();
        vaultInventory.accept(weightChecker);
        appraisalLines.add(weightChecker.summary());
        System.out.println("  >> " + weightChecker.summary());

        // ── Room 3: Trap Room — save checkpoint BEFORE entering ──────────
        System.out.println("\n--- Room 3: Trap Room (save checkpoint before) ---");
        caretaker.push(hero.saveMemento("Before Trap Room"));
        roomsEntered++;

        // Hero triggers a catastrophic trap
        System.out.println("  ⚡ TRAP TRIGGERED — cursed time-trap drains hero!");
        trapsTriggered++;
        hero.takeDamage(50);
        hero.spendMana(40);
        hero.spendGold(200);
        hero.drop(timeRing.getName()); // ring vanishes in the trap
        System.out.println("  After trap: " + hero.statusLine());

        // Hero is in bad shape — REWIND using Memento
        System.out.println("\n  ⏪ CHRONOMANCER'S REWIND activated!");
        caretaker.pop().ifPresent(m -> {
            hero.restoreMemento(m);
        });
        rewindsUsed++;
        System.out.println("  Post-rewind: " + hero.statusLine());

        // ── Room 4: Boss Antechamber — second checkpoint ─────────────────
        System.out.println("\n--- Room 4: Boss Antechamber (checkpoint) ---");
        caretaker.push(hero.saveMemento("Boss Antechamber"));
        hero.earnGold(300);
        hero.pickUp(voidArmor.getName());
        roomsEntered++;
        System.out.println("  Hero earns 300g and equips the Void-Crystal Plate.");
        System.out.println("  State: " + hero.statusLine());

        // ── Room 5: Final Boss — hero barely survives ────────────────────
        System.out.println("\n--- Room 5: Chronomancer Boss ---");
        roomsEntered++;
        System.out.println("  Boss attacks for 35 damage and drains 20 mana!");
        hero.takeDamage(35);
        hero.spendMana(20);
        System.out.println("  Hero uses the Elixir of Ages to recover!");
        hero.heal(40);
        hero.spendMana(10);
        System.out.println("  Hero defeats the Chronomancer! Victory!");
        System.out.println("  Final state: " + hero.statusLine());

        // ── Print checkpoint history ──────────────────────────────────────
        System.out.println("\n--- Caretaker checkpoint history ---");
        caretaker.printHistory();

        // ── Build result ──────────────────────────────────────────────────
        VaultRunResult result = new VaultRunResult(
            hero.getName(),
            roomsEntered,
            trapsTriggered,
            rewindsUsed,
            hero.getGold(),
            hero.getHp(),
            appraisalLines
        );

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║    VAULT RUN COMPLETE                ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println(result.summary());

        return result;
    }
}

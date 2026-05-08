package com.narxoz.rpg.vault;

import java.util.List;

/**
 * Immutable summary of a complete Chronomancer's Vault run.
 */
public final class VaultRunResult {
    private final String heroName;
    private final int    roomsEntered;
    private final int    trapsTriggered;
    private final int    rewindsUsed;
    private final int    goldCollected;
    private final int    finalHp;
    private final List<String> appraisalLines;

    public VaultRunResult(String heroName,
                          int roomsEntered,
                          int trapsTriggered,
                          int rewindsUsed,
                          int goldCollected,
                          int finalHp,
                          List<String> appraisalLines) {
        this.heroName       = heroName;
        this.roomsEntered   = roomsEntered;
        this.trapsTriggered = trapsTriggered;
        this.rewindsUsed    = rewindsUsed;
        this.goldCollected  = goldCollected;
        this.finalHp        = finalHp;
        this.appraisalLines = List.copyOf(appraisalLines);
    }

    public String heroName()       { return heroName; }
    public int roomsEntered()      { return roomsEntered; }
    public int trapsTriggered()    { return trapsTriggered; }
    public int rewindsUsed()       { return rewindsUsed; }
    public int goldCollected()     { return goldCollected; }
    public int finalHp()           { return finalHp; }
    public List<String> appraisalLines() { return appraisalLines; }

    public String summary() {
        return "VaultRun[hero=" + heroName
               + ", rooms=" + roomsEntered
               + ", traps=" + trapsTriggered
               + ", rewinds=" + rewindsUsed
               + ", gold=" + goldCollected
               + ", finalHP=" + finalHp + "]";
    }
}
